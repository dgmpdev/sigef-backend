package ci.dgmp.sigefbackend.metier.model.dtos;

import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.CreateActionGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.UpdateActionGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.CreateActiviteGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.UpdateActiviteGroup;
import ci.dgmp.sigefbackend.metier.model.validators.ExistingActId;
import ci.dgmp.sigefbackend.metier.model.validators.ExistingActionId;
import ci.dgmp.sigefbackend.metier.model.validators.ExistingGesCode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ExistingActId(groups = {UpdateActionGroup.class, UpdateActiviteGroup.class})
@ExistingActionId(groups = {CreateActiviteGroup.class, UpdateActiviteGroup.class})
public class ActionActivteDTO
{
    private Long actId;
    private String actLibelle;
    @ExistingGesCode(groups = {CreateActionGroup.class, UpdateActionGroup.class, CreateActiviteGroup.class, UpdateActiviteGroup.class})
    @NotNull(groups = {CreateActionGroup.class, CreateActiviteGroup.class})
    private Long actGesCode;
    private String actGesLibelle;
    private int actNumOrdre;
    @ExistingActionId(groups = {CreateActiviteGroup.class, UpdateActiviteGroup.class}, allowNull = true)
    @NotNull(groups = {CreateActiviteGroup.class})
    private Long actParentId;
    private String actParentLibelle;
    private String actTypeCode;
    private String actTypeName;
}