package group5.eeet2580_project.service.email;
import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.entity.User;

public interface EmailService {
    void sendOrderConfirmationEmail(User user, SprayOrder sprayOrder);
}
