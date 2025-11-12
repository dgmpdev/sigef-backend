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
@Table(name = "evaluation")
@Audited
public class Evaluation extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVALUATION_ID_GEN")
    @SequenceGenerator(name = "EVALUATION_ID_GEN", sequenceName = "EVALUATION_ID_SEQ", initialValue = 1)
    private Long evalId;

    private int evalNote;

    @ManyToOne
    @JoinColumn(name = "PARTICIPANT_ID")
    private Participant evalParticipant;

    @ManyToOne
    @JoinColumn(name = "CRITERE_ID")
    private CritereEvaluation evalCritere;

    private String evalObservation;

    @ManyToOne
    @JoinColumn(name = "DETAIL_PROGRAMME_ID")
    private DetailProgramme evalDetailProgramme;

    @ManyToOne
    @JoinColumn(name = "PROGRAMME_ID")
    private ProgrammeFormation evalProgramme;

    @Transient
    private AuditableEntity auditableEntity;
}
