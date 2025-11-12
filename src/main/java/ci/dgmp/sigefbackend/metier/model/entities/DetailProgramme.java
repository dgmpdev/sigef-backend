package ci.dgmp.sigefbackend.metier.model.entities;

import jakarta.persistence.*;
import ci.dgmp.sigefbackend.admin.security.audit.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detail_programme")
@Audited
public class DetailProgramme extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETAIL_PROGRAMME_ID_GEN")
    @SequenceGenerator(name = "DETAIL_PROGRAMME_ID_GEN", sequenceName = "DETAIL_PROGRAMME_ID_SEQ", initialValue = 1)
    private Long detProgId;

    private LocalDate detProgDate;

    private LocalDateTime detProgDebut;

    private LocalDateTime detProgFin;

    private String detProgActivite;

    private String detProgObjet;

    private String detProgIntervenant;

    @ManyToOne
    @JoinColumn(name = "PROGRAMME_ID")
    private ProgrammeFormation programme;

    @ManyToOne
    @JoinColumn(name = "SEANCE_ID")
    private SeanceFormation detProgSeance;

    @ManyToOne
    @JoinColumn(name = "MODULE_ID")
    private CategorieModule module;

    @Transient
    private AuditableEntity auditableEntity;
}
