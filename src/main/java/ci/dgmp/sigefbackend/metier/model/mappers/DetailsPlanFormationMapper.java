package ci.dgmp.sigefbackend.metier.model.mappers;

import ci.dgmp.sigefbackend.admin.types.model.entities.Type;
import ci.dgmp.sigefbackend.metier.model.dtos.ActionActivteDTO;
import ci.dgmp.sigefbackend.metier.model.entities.ActionActivte;
import ci.dgmp.sigefbackend.metier.model.entities.Gestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetailsPlanFormationMapper
{
    @Mapping(target = "actType", expression = "java(mapToType(\"ACTION\"))")
    @Mapping(target = "actGestion", expression = "java(mapToGestion(dto.getActGesCode()))")
    ActionActivte mapToAction(ActionActivteDTO dto);

    @Mapping(target = "actType", expression = "java(mapToType(\"ACTIVITE\"))")
    @Mapping(target = "actGestion", expression = "java(mapToGestion(dto.getActGesCode()))")
    @Mapping(target = "action", expression = "java(mapToAction(dto.getActParentId()))")
    ActionActivte mapToActivite(ActionActivteDTO dto);

    @Mapping(target = "actGesCode", source = "actGestion.gesCode")
    @Mapping(target = "actGesLibelle", source = "actGestion.gesLibelle")
    @Mapping(target = "actParentId", source = "action.actId")
    @Mapping(target = "actParentLibelle", source = "action.actLibelle")
    @Mapping(target = "actTypeCode", source = "actType.code")
    @Mapping(target = "actTypeName", source = "actType.name")
    ActionActivteDTO mapToDto(ActionActivte entity);

    default Type mapToType(String typeCode)
    {
        if (typeCode == null) return null;
        return new Type(typeCode);
    }

    default Gestion mapToGestion(Long gesCode)
    {
        if (gesCode == null) return null;
        return new Gestion(gesCode);
    }

    default ActionActivte mapToAction(Long actId)
    {
        if (actId == null) return null;
        return new ActionActivte(actId);
    }
}
