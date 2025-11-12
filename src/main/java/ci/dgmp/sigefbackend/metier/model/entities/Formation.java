package ci.dgmp.sigefbackend.metier.model.entities;

import jakarta.persistence.*;
import ci.dgmp.sigefbackend.admin.security.audit.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formation")
@Audited
public class Formation extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMATION_ID_GEN")
    @SequenceGenerator(name = "FORMATION_ID_GEN", sequenceName = "FORMATION_ID_SEQ", initialValue = 1)
    private Long formId;

    private String formCible;

    private String formDescriptionCible;

    private String formTheme;

    private String formPeriode;

    private int formNbrParticipant;

    private int formNumOrdre;

    private String attribute1;

    @Transient
    private AuditableEntity auditableEntity;
}
