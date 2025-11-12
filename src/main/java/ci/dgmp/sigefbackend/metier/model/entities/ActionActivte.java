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
@Table(name = "action_activite")
@Audited
public class ActionActivte extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACTION_ACTIVITE_ID_GEN")
    @SequenceGenerator(name = "ACTION_ACTIVITE_ID_GEN", sequenceName = "ACTION_ACTIVITE_ID_SEQ", initialValue = 1)
    private Long actId;

    private String actLibelle;

    @ManyToOne
    @JoinColumn(name = "ACT_GESTION_ID")
    private Gestion actGestion;

    private int actNumOrdre;

    @ManyToOne
    @JoinColumn(name = "ACTION_PARENT_ID")
    private ActionActivte action;

    @Transient
    private AuditableEntity auditableEntity;//khkh
}
