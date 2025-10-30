package ci.dgmp.sigefbackend.admin.notification.controller.dao;

import ci.dgmp.sigefbackend.admin.notification.model.entities.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailNotificationRepo extends JpaRepository<EmailNotification, Long>
{

}
