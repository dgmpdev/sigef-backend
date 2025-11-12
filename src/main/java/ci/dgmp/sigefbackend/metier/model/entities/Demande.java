package ci.dgmp.sigefbackend.metier.model.entities;

import ci.dgmp.sigefbackend.admin.structures.model.entities.Structure;
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
@Table(name = "demande")
@Audited
public class Demande extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEMANDE_ID_GEN")
    @SequenceGenerator(name = "DEMANDE_ID_GEN", sequenceName = "DEMANDE_ID_SEQ", initialValue = 1)
    private Long demId;

    @ManyToOne
    @JoinColumn(name = "DEM_TYPE_ID")
    private Type demType;

    private String demObjet;

    private String demDescription;

    @ManyToOne
    @JoinColumn(name = "DEM_REQUERANT_ID")
    private Structure demRequerant;

    @ManyToOne
    @JoinColumn(name = "DEM_STATUT_ID")
    private Type demStatut;

    @Transient
    private AuditableEntity auditableEntity;
}
