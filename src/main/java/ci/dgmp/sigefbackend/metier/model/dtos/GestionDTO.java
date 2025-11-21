package ci.dgmp.sigefbackend.metier.model.dtos;

import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.CreateGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.UpdateGroup;
import ci.dgmp.sigefbackend.metier.model.validators.ExistingGesCode;
import ci.dgmp.sigefbackend.metier.model.validators.UniqueGesCode;
import ci.dgmp.sigefbackend.metier.model.validators.UniqueGesLibelle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ExistingGesCode(groups = {UpdateGroup.class}) @UniqueGesLibelle(groups = {UpdateGroup.class})
public class GestionDTO
{
    @UniqueGesCode(groups = {CreateGroup.class}, allowNull = true)
    private Long gesCode;
    private boolean gesCourant;
    @UniqueGesLibelle(groups = {CreateGroup.class})
    private String gesLibelle;
    private String statut;
}
