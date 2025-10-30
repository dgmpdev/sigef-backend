package ci.dgmp.sigefbackend.admin.security.model.views;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "v_structure")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VUser
{
    @Id
    private Long userId;
    private String email;
    private String matricule;
    private String firstName;
    private String lastName;
    private String tel;
    private String grade;
    private LocalDate changePasswordDate;
    private boolean activated = false;
    private boolean notBlocked = true;
    private LocalDateTime lastLogin;

    private Long strId;
    private String strName;
    private String strSigle;
    private String strTel;
    private String strAddress;
    private String situationGeo;
    private String strTypeName;
    private String strTypeCode;
    private Long parentId;
    private String parentName;
    private String parentSigle;
    private Long strLevel;
    private Long respoId;
    private String respoName;
    private String respoMatricule;
    private String chaineSigles;

    private String searchText;
}
