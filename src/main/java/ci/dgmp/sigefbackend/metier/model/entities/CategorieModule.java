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
@Table(name = "categorie_module")
@Audited
public class CategorieModule extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIE_MODULE_ID_GEN")
    @SequenceGenerator(name = "CATEGORIE_MODULE_ID_GEN", sequenceName = "CATEGORIE_MODULE_ID_SEQ", initialValue = 1)
    private Long catModId;

    private String catModLibelle;

    private String catModDescription;

    @ManyToOne
    @JoinColumn(name = "CATEGORIE_PARENT_ID")
    private CategorieModule categorie;

    @Transient
    private AuditableEntity auditableEntity;
}
