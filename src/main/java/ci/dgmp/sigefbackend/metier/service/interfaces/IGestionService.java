package ci.dgmp.sigefbackend.metier.service.interfaces;

import ci.dgmp.sigefbackend.metier.model.dtos.GestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IGestionService
{
    GestionDTO create(GestionDTO dto);
    GestionDTO update(GestionDTO dto);
    Page<GestionDTO> search(String key, PageRequest pageRequest);
    GestionDTO setAsCourant(Long gesCode);
}
