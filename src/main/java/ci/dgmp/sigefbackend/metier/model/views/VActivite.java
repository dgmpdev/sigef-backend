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
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_activite", schema = "sigef")
public class VActivite
{
    @Id
    @Column(name = "act_id")
    private Long actId;

    @Size(max = 255)
    @Column(name = "act_libelle")
    private String actLibelle;

    @Column(name = "ges_code")
    private Long gesCode;

    @Size(max = 255)
    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "type_name", length = Integer.MAX_VALUE)
    private String typeName;

    @Column(name = "act_num_ordre")
    private Integer actNumOrdre;

    @Column(name = "num_ordre_code", length = Integer.MAX_VALUE)
    private String numOrdreCode;

    @Column(name = "action_parent_id")
    private Long actionParentId;

    @Column(name = "form_cible", length = Integer.MAX_VALUE)
    private String formCible;

    @Column(name = "form_description_cible", length = Integer.MAX_VALUE)
    private String formDescriptionCible;

    @Column(name = "form_theme", length = Integer.MAX_VALUE)
    private String formTheme;

    @Column(name = "form_periode", length = Integer.MAX_VALUE)
    private String formPeriode;

    @Column(name = "form_nbr_participant")
    private Integer formNbrParticipant;

    @Column(name = "form_num_ordre")
    private Integer formNumOrdre;

}