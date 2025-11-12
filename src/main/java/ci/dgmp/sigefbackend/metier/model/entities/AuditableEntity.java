package ci.dgmp.sigefbackend.metier.model.entities;

import ci.dgmp.sigefbackend.admin.security.model.entities.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@Audited
public abstract class AuditableEntity
{
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by", updatable = false)
    private AppUser createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private AppUser updatedBy;

    @Column(name = "connexion_id")
    private String connexionId;
}
