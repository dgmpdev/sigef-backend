package ci.dgmp.sigefbackend.metier.model.views;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "V_DETAILS_PLAN_FORMATION")
@Audited
public class VDetailsPlanFormation
{
    @Id
    private Long rId;
    private Long id;
    private String formCible;
    private String formDescriptionCible;
    private String formTheme;
    private String formPeriode;
    private int formNbrParticipant;
    private int formNumOrdre;
    private String actLibelle;
    private String typeCode;
    private String typeName;
    private String numOrdreCode;
    @Column(name = "action_parent_id")
    private Long actionParentId;
    @Column(name = "search_text")
    private String searchText;
    @Column(name = "ges_code")
    private Long gesCode;

}