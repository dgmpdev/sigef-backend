package ci.dgmp.sigefbackend.metier.model.mappers;

import ci.dgmp.sigefbackend.metier.model.dtos.GestionDTO;
import ci.dgmp.sigefbackend.metier.model.entities.Gestion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GestionMapper
{

    Gestion mapToEntity(GestionDTO dto);
    GestionDTO mapToDto(Gestion entity);
    Gestion partialUpdate(GestionDTO dto, @MappingTarget Gestion entity);
}
