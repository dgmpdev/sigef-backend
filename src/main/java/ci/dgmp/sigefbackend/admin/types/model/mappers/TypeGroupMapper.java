package ci.dgmp.sigefbackend.admin.types.model.mappers;

import ci.dgmp.sigefbackend.admin.types.model.dtos.TypeGroupDTO;
import ci.dgmp.sigefbackend.admin.types.model.entities.TypeGroup;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TypeGroupMapper
{
    TypeGroupDTO mapToDto(TypeGroup entity);
    TypeGroup mapToEntity(TypeGroupDTO dto);

    TypeGroup updateTypeGroupFromDto(TypeGroupDTO dto, @MappingTarget TypeGroup t);
}
