package group5.eeet2580_project.service.email;
import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendOrderConfirmationEmail(User user, SprayOrder sprayOrder);
    void sendOrderCancelledEmail(User user, SprayOrder sprayOrder);
    void sendOrderAssignedEmail(User user, SprayOrder sprayOrder, String sprayerNames);
    void sendOrderInProgressEmail(User user, SprayOrder sprayOrder);
    void sendOrderCompletedEmail(User user, SprayOrder sprayOrder);
    void sendEmail(String to, String subject, String text);
}
