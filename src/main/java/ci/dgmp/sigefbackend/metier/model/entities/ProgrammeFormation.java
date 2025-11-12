package ci.dgmp.sigefbackend.metier.model.entities;

import ci.dgmp.sigefbackend.admin.types.model.entities.Type;
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
@Table(name = "programme_formation")
@Audited
public class ProgrammeFormation extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROGRAMME_FORMATION_ID_GEN")
    @SequenceGenerator(name = "PROGRAMME_FORMATION_ID_GEN", sequenceName = "PROGRAMME_FORMATION_ID_SEQ", initialValue = 1)
    private Long progFormId;

    private String progFormObjet;

    private String progFormLieu;

    @ManyToOne
    @JoinColumn(name = "STATUT_ID")
    private Type progFormStatut;

    @Transient
    private AuditableEntity auditableEntity;
}
