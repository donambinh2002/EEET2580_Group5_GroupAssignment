package group5.eeet2580_project.service.email;

import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.entity.User;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendOrderConfirmationEmail(User user, SprayOrder sprayOrder) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(user.getEmail());
            helper.setSubject("Spray Order Confirmation");
            helper.setText("Dear " + user.getUsername() + ",\n\nYour spray order has been created successfully.\n\nOrder Details:\n" +
                    "Crop Type: " + sprayOrder.getCropType() + "\n" +
                    "Farmland Area: " + sprayOrder.getFarmlandArea() + "\n" +
                    "Desired Date: " + sprayOrder.getDesiredDate() + "\n" +
                    "Desired Time: " + sprayOrder.getDesiredTime() + "\n" +
                    "Total Cost: " + sprayOrder.getTotalCost() + "\n\nThank you for using our service!");

            mailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
