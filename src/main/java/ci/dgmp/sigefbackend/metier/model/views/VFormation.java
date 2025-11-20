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
@Table(name = "v_formation", schema = "sigef")
public class VFormation
{
    @Id
    @Column(name = "form_id")
    private Long formId;

    @Size(max = 255)
    @Column(name = "form_cible")
    private String formCible;

    @Size(max = 255)
    @Column(name = "form_description_cible")
    private String formDescriptionCible;

    @Size(max = 255)
    @Column(name = "form_theme")
    private String formTheme;

    @Size(max = 255)
    @Column(name = "form_periode")
    private String formPeriode;

    @Column(name = "form_nbr_participant")
    private Integer formNbrParticipant;

    @Column(name = "form_num_ordre")
    private Integer formNumOrdre;

    @Column(name = "activite_id")
    private Long activiteId;

    @Size(max = 255)
    @Column(name = "activite")
    private String activite;

    @Column(name = "action_id")
    private Long actionId;

    @Size(max = 255)
    @Column(name = "action")
    private String action;

    @Column(name = "num_ordre")
    private Integer numOrdre;

    @Column(name = "num_ordre_code", length = Integer.MAX_VALUE)
    private String numOrdreCode;

    @Column(name = "type_code", length = Integer.MAX_VALUE)
    private String typeCode;

    @Column(name = "type_name", length = Integer.MAX_VALUE)
    private String typeName;

}