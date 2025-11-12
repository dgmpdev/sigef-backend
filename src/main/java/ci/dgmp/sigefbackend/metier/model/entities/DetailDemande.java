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
@Table(name = "detail_demande")
@Audited
public class DetailDemande extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETAIL_DEMANDE_ID_GEN")
    @SequenceGenerator(name = "DETAIL_DEMANDE_ID_GEN", sequenceName = "DETAIL_DEMANDE_ID_SEQ", initialValue = 1)
    private Long detDemId;

    @ManyToOne
    @JoinColumn(name = "DEMANDE_ID")
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "CIBLE_ID")
    private Structure cible;

    @ManyToOne
    @JoinColumn(name = "CATEGORIE_ID")
    private CategorieModule categorie;

    @ManyToOne
    @JoinColumn(name = "FORMATION_ID")
    private Formation detDemFormation;

    @Transient
    private AuditableEntity auditableEntity;
}
