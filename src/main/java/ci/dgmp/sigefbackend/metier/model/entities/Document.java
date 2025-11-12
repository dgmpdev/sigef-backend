package ci.dgmp.sigefbackend.metier.model.entities;

import ci.dgmp.sigefbackend.admin.types.model.entities.Type;
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
@Table(name = "document")
@Audited
public class Document extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_ID_GEN")
    @SequenceGenerator(name = "DOCUMENT_ID_GEN", sequenceName = "DOCUMENT_ID_SEQ", initialValue = 1)
    private Long docId;

    private String docObjet;

    private String docRef;

    private LocalDate docDate;

    private LocalDateTime docDateReception;

    private Long docNumChrono;

    @ManyToOne
    @JoinColumn(name = "DOC_TYPE_ID")
    private Type docType;

    private String docPath;

    private String docName;

    private Long docTableId;

    private String docTableName;

    @Transient
    private AuditableEntity auditableEntity;
}
