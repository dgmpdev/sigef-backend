package ci.dgmp.sigefbackend.metier.model.entities;

import jakarta.persistence.*;
import ci.dgmp.sigefbackend.admin.security.audit.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seance_formation")
@Audited
public class SeanceFormation extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEANCE_FORMATION_ID_GEN")
    @SequenceGenerator(name = "SEANCE_FORMATION_ID_GEN", sequenceName = "SEANCE_FORMATION_ID_SEQ", initialValue = 1)
    private Long sfId;

    private LocalDateTime sfDateDebut;

    private LocalDateTime sfDateFin;

    private String sfDescription;

    @Transient
    private AuditableEntity auditableEntity;
}
