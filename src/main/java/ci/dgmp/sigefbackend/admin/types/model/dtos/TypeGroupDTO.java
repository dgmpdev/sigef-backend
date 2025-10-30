package ci.dgmp.sigefbackend.admin.types.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ci.dgmp.sigefbackend.admin.types.model.validators.ExistingGroupCode;
import ci.dgmp.sigefbackend.admin.types.model.validators.UniqueGroupCode;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.CreateGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TypeGroupDTO
{
    @NotNull(message = "Le code du groupe ne peut être null")
    @NotBlank(message = "Le code du groupe ne peut être null")
    @UniqueGroupCode(groups = {CreateGroup.class})
    @ExistingGroupCode(groups = {UpdateGroup.class})
    private String groupCode;
    @NotNull(message = "Le nom du groupe ne peut être null")
    @NotBlank(message = "Le nom du groupe ne peut être null")
    private String name;

    public TypeGroupDTO(String groupCode) {
        this.groupCode = groupCode;
    }
}
