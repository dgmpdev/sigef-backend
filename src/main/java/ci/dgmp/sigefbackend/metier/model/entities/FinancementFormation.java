package ci.dgmp.sigefbackend.metier.model.entities;

import ci.dgmp.sigefbackend.admin.structures.model.entities.Structure;
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
@Table(name = "financement_formation")
@Audited
public class FinancementFormation extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FINANCEMENT_FORMATION_ID_GEN")
    @SequenceGenerator(name = "FINANCEMENT_FORMATION_ID_GEN", sequenceName = "FINANCEMENT_FORMATION_ID_SEQ", initialValue = 1)
    private Long finId;

    @ManyToOne
    @JoinColumn(name = "BAILLEUR_ID")
    private Structure finBailleur;

    @ManyToOne
    @JoinColumn(name = "FORMATION_ID")
    private Formation finFormation;

    @Transient
    private AuditableEntity auditableEntity;
}
