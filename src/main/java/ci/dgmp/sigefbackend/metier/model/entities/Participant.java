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
@Table(name = "participant")
@Audited
public class Participant extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTICIPANT_ID_GEN")
    @SequenceGenerator(name = "PARTICIPANT_ID_GEN", sequenceName = "PARTICIPANT_ID_SEQ", initialValue = 1)
    private Long partId;

    private String partMatricule;

    private String partNom;

    private String partPrenom;

    private String partGenre;

    private String partTel;

    private String partEmail;

    @ManyToOne
    @JoinColumn(name = "FONCTION_ID")
    private Type partFonction;

    @ManyToOne
    @JoinColumn(name = "STRUCTURE_ID")
    private Structure partStructure;

    @ManyToOne
    @JoinColumn(name = "SEANCE_ID")
    private SeanceFormation partSeance;

    @Transient
    private AuditableEntity auditableEntity;
}
