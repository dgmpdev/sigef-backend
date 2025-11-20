package ci.dgmp.sigefbackend.metier.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter @Setter @Entity
@Immutable
@Table(name = "v_action", schema = "sigef")
public class VAction
{
    @Id @Column(name = "act_id")
    private Long actId;
    @Column(name = "act_libelle")
    private String actLibelle;
    @Column(name = "ges_code")
    private Long gesCode;
    @Column(name = "type_code")
    private String typeCode;
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "act_num_ordre")
    private Integer actNumOrdre;
    @Column(name = "num_ordre_code")
    private String numOrdreCode;
    @Column(name = "form_cible")
    private String formCible;
    @Column(name = "form_description_cible")
    private String formDescriptionCible;
    @Column(name = "form_theme")
    private String formTheme;
    @Column(name = "form_periode")
    private String formPeriode;
    @Column(name = "form_nbr_participant")
    private Integer formNbrParticipant;
    @Column(name = "form_num_ordre")
    private Integer formNumOrdre;
}