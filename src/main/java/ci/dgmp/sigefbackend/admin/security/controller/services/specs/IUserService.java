package ci.dgmp.sigefbackend.admin.security.controller.services.specs;

import ci.dgmp.sigefbackend.admin.security.model.dtos.AuthResponse;
import jakarta.transaction.Transactional;
import ci.dgmp.sigefbackend.admin.security.model.dtos.CreateUserDTO;
import ci.dgmp.sigefbackend.admin.security.model.dtos.UserDTO;
import ci.dgmp.sigefbackend.admin.security.model.entities.AuthToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IUserService
{
    UserDTO createUser(UserDTO user);

    UserDTO createUserWithProfile(CreateUserDTO user);

    UserDTO updateUser(UserDTO user);

    void changePassword(UserDTO user);

    void resetPassword(UserDTO user);

    void blockUser(Long userId);

    void unblockUser(Long userId);

    void sendResetPasswordEmail(Long userId);

    void sendResetPasswordEmail(String email);

    void sendActivationEmail(Long userId);

    void activateAccount(UserDTO user);

    Page<UserDTO> searchUsers(String key, Long strId, PageRequest pageRequest);

    List<UserDTO> getVisibleUsers();

    UserDTO findByUsername(String username);

    AuthToken generateAuthToken(Long userId);
    void invalidateAuthToken(String token);

    @Transactional
    AuthResponse login(UserDTO dto);

    @Transactional
    AuthResponse refreshToken(Long userId);
}
