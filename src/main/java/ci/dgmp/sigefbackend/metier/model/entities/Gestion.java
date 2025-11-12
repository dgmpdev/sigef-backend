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
@Table(name = "gestion")
@Audited
public class Gestion extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_ID_GEN")
    @SequenceGenerator(name = "GESTION_ID_GEN", sequenceName = "GESTION_ID_SEQ", initialValue = 1)
    private Long gesCode;

    private boolean gesCourant;

    private String gesLibelle;

    @Transient
    private AuditableEntity auditableEntity;
}
