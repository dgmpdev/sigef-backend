package ci.dgmp.sigefbackend.admin.security.model.entities;

import jakarta.persistence.*;
import ci.dgmp.sigefbackend.admin.security.audit.AuditableEntity;
import ci.dgmp.sigefbackend.admin.types.model.entities.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity @Table(name = "app_authority")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Audited
public class AppAuthority extends AuditableEntity
{
    @Id
    private String code;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    @ManyToOne @JoinColumn(name = "type_code") @NotAudited
    private Type type;
    @ManyToOne @JoinColumn(name = "privilege_type_code") @NotAudited
    private Type privilegeType;
    private Long profileMaxAssignation;

    public AppAuthority(String code)
    {
        this.code = code;
    }
}