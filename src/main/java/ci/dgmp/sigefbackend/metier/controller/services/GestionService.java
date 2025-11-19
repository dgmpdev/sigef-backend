package ci.dgmp.sigefbackend.metier.controller.services;

import ci.dgmp.sigefbackend.admin.exceptions.AppException;
import ci.dgmp.sigefbackend.metier.model.dtos.GestionDTO;
import ci.dgmp.sigefbackend.metier.model.entities.Gestion;
import ci.dgmp.sigefbackend.metier.model.mappers.GestionMapper;
import ci.dgmp.sigefbackend.metier.repositories.GestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class GestionService implements IGestionService
{
    private final GestionRepo gestionRepository;
    private final GestionMapper gestionMapper;

    @Override
    public GestionDTO create(GestionDTO dto)
    {
        Gestion entity = gestionMapper.mapToEntity(dto);
        entity = gestionRepository.save(entity);
        return gestionMapper.mapToDto(entity);
    }

    @Override
    public GestionDTO update(GestionDTO dto)
    {
        if (dto.getGesCode() == null) throw new AppException("L'identifiant de la gestion est obligatoire");
        Gestion entity = gestionRepository.findById(dto.getGesCode()).orElseThrow(() -> new AppException("Gestion introuvable"));
        entity.setGesLibelle(dto.getGesLibelle());
        entity = gestionRepository.save(entity);
        return gestionMapper.mapToDto(entity);
    }

    @Override
    public Page<GestionDTO> search(String key, PageRequest pageRequest)
    {
        return gestionRepository.search(key, pageRequest);
    }

    @Override
    @Transactional
    public GestionDTO setAsCourant(Long gesCode)
    {
        if (gesCode == null) throw new AppException("Le code de la gestion est obligatoire");
        Gestion target = gestionRepository.findById(gesCode).orElseThrow(() -> new AppException("Gestion introuvable"));
        gestionRepository.resetAllCourant();
        target.setGesCourant(true);
        return gestionMapper.mapToDto(target);
    }
}
