package ci.dgmp.sigefbackend.metier.service.implementation;

import ci.dgmp.sigefbackend.admin.types.model.entities.Type;
import ci.dgmp.sigefbackend.admin.utilities.StringUtils;
import ci.dgmp.sigefbackend.metier.model.dtos.ActionActivteDTO;
import ci.dgmp.sigefbackend.metier.model.entities.ActionActivte;
import ci.dgmp.sigefbackend.metier.model.mappers.DetailsPlanFormationMapper;
import ci.dgmp.sigefbackend.metier.model.views.VDetailsPlanFormation;
import ci.dgmp.sigefbackend.metier.model.views.VDetailsPlanFormationRepo;
import ci.dgmp.sigefbackend.metier.repositories.ActionActivteRepo;
import ci.dgmp.sigefbackend.metier.service.interfaces.IDetailsPlanFormationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class DetailsPlanFormationService implements IDetailsPlanFormationService
{
    private final DetailsPlanFormationMapper dpfMapper;
    private final ActionActivteRepo actRepo;
    private final VDetailsPlanFormationRepo vdpfRepo;

    /**
     * Synchronise les numéros d'ordre en décalant de +1 toutes les entrées
     * ayant actNumOrdre >= newNumOrdre dans une même hiérarchie.
     */

    @Transactional
    public void synchronizeActionsOrder(Long currentActionId, Integer newNumOrdre, Long gesCode)
    {
        List<ActionActivte> actions = currentActionId == null ? actRepo.findActionsByGesCode(gesCode) : actRepo.findActionsByGesCodeAndNotActionId(gesCode, currentActionId);
        boolean existsWithSameOrder = currentActionId == null ? actRepo.actionExistsWithSameOrder(gesCode, newNumOrdre) : actRepo.actionExistsWithSameOrderAndNotActionId(gesCode, newNumOrdre, currentActionId);
        addOneToNumOrdreIfExists(newNumOrdre, existsWithSameOrder, actions);
    }

    private static void addOneToNumOrdreIfExists(Integer newNumOrdre, boolean existsWithSameOrder, List<ActionActivte> acts)
    {
        if (existsWithSameOrder)
        {
            acts.stream()
                    .filter(a -> a.getActNumOrdre() >= newNumOrdre)
                    .forEach(a -> a.setActNumOrdre(a.getActNumOrdre() + 1));
        }
    }

    @Transactional
    public void synchronizeActivitesOrder(Long currentActiviteId, Integer newNumOrdre, Long actionParentId)
    {
        List<ActionActivte> activites = currentActiviteId == null ? actRepo.findActivitesByActionId(actionParentId) : actRepo.findActivitesByActionIdAndNotActiviteId(actionParentId, currentActiviteId);
        boolean existsWithSameOrder = currentActiviteId == null ? actRepo.activiteExistsWithSameOrder(actionParentId, newNumOrdre) : actRepo.activiteExistsWithSameOrderAndNotActiviteId(actionParentId, newNumOrdre, currentActiviteId);

        addOneToNumOrdreIfExists(newNumOrdre, existsWithSameOrder, activites);
    }


    @Override @Transactional
    public ActionActivteDTO createAction(ActionActivteDTO dto)
    {
        this.synchronizeActionsOrder(null, dto.getActNumOrdre(), dto.getActGesCode());
        ActionActivte action = dpfMapper.mapToAction(dto);
        action = actRepo.save(action);
        this.synchronizeActionsOrder(action.getActId(), dto.getActNumOrdre(), dto.getActGesCode());
        return dpfMapper.mapToDto(action);
    }

    @Override @Transactional
    public ActionActivteDTO updateAction(ActionActivteDTO dto)
    {
        this.synchronizeActionsOrder(null, dto.getActNumOrdre(), dto.getActGesCode());
        ActionActivte action = actRepo.findById(dto.getActId()).orElseThrow(()->new RuntimeException("Action introuvable"));
        action.setActLibelle(dto.getActLibelle());
        return dpfMapper.mapToDto(action);
    }

    @Override
    public ActionActivteDTO createActivie(ActionActivteDTO dto)
    {
        this.synchronizeActivitesOrder(null, dto.getActNumOrdre(), dto.getActParentId());
        ActionActivte activite = dpfMapper.mapToActivite(dto);
        activite = actRepo.save(activite);
        return dpfMapper.mapToDto(activite);
    }

    @Override
    public ActionActivteDTO updateActivite(ActionActivteDTO dto)
    {
        ActionActivte activite = actRepo.findById(dto.getActId()).orElseThrow(()->new RuntimeException("Action introuvable"));
        activite.setActLibelle(dto.getActLibelle());
        activite.setAction( new ActionActivte(dto.getActParentId()));
        this.synchronizeActivitesOrder(activite.getActId(), dto.getActNumOrdre(), dto.getActParentId());
        return dpfMapper.mapToDto(activite);
    }

    @Override
    public ActionActivteDTO getActionDTO(Long gesCode)
    {
        int nextNumOrdre = actRepo.getNextActionNumOrdre(gesCode);
        ActionActivteDTO dto = new ActionActivteDTO();
        dto.setActGesCode(gesCode);
        dto.setActTypeCode("ACTION");
        dto.setActTypeName("Action");
        dto.setActNumOrdre(nextNumOrdre);
        return dto;
    }

    @Override
    public ActionActivteDTO getActiviteDTO(Long actionId)
    {
        int nextNumOrdre = actRepo.getNextActiviteNumOrdre(actionId);
        ActionActivteDTO dto = new ActionActivteDTO();
        dto.setActParentId(actionId);
        dto.setActTypeCode("ACTIVITE");
        dto.setActTypeName("Activité");
        dto.setActNumOrdre(nextNumOrdre);
        return dto;
    }

    @Override
    public Page<VDetailsPlanFormation> search(String key, Long gesCode, Pageable pageable)
    {
        key = StringUtils.stripAccentsToUpperCase(key);
        return vdpfRepo.search(key, gesCode, pageable);
    }
}
