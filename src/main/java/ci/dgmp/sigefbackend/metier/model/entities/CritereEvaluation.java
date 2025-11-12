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
@Table(name = "critere_evaluation")
@Audited
public class CritereEvaluation extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRITERE_EVALUATION_ID_GEN")
    @SequenceGenerator(name = "CRITERE_EVALUATION_ID_GEN", sequenceName = "CRITERE_EVALUATION_ID_SEQ", initialValue = 1)
    private Long critId;

    private String critLibelle;

    private String critDescription;

    @ManyToOne
    @JoinColumn(name = "ASPECT_PARENT_ID")
    private CritereEvaluation aspect;

    private int critNumOrdre;

    @Transient
    private AuditableEntity auditableEntity;
}
