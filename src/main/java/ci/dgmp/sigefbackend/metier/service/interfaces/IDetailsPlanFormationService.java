package ci.dgmp.sigefbackend.metier.service.interfaces;

import ci.dgmp.sigefbackend.metier.model.dtos.ActionActivteDTO;
import ci.dgmp.sigefbackend.metier.model.views.VDetailsPlanFormation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface IDetailsPlanFormationService
{
    ActionActivteDTO createAction(ActionActivteDTO dto);
    ActionActivteDTO updateAction(ActionActivteDTO dto);
    ActionActivteDTO createActivie(ActionActivteDTO dto);
    ActionActivteDTO updateActivite(ActionActivteDTO dto);
    ActionActivteDTO getActionDTO(Long gesCode);
    ActionActivteDTO getActiviteDTO(Long actionId);
    Page<VDetailsPlanFormation> search(String key, Long gesCode, Pageable pageable);
}
