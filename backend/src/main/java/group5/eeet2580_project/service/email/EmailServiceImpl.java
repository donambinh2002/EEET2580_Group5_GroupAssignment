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
        sendEmail(user.getEmail(), "Spray Order Confirmation", "Dear " + user.getUsername() + ",\n\nYour spray order has been created successfully.\n\nOrder Details:\n" +
                "Crop Type: " + sprayOrder.getCropType() + "\n" +
                "Farmland Area: " + sprayOrder.getFarmlandArea() + "\n" +
                "Desired Date: " + sprayOrder.getDesiredDate() + "\n" +
                "Desired Time: " + sprayOrder.getDesiredTime() + "\n" +
                "Total Cost: " + sprayOrder.getTotalCost() + "\n\nThank you for using our service!");
    }

    @Override
    public void sendOrderCancelledEmail(User user, SprayOrder sprayOrder) {
        sendEmail(user.getEmail(), "Spray Order Cancelled", "Dear " + user.getUsername() + ",\n\nYour spray order has been cancelled.\n\nOrder Details:\n" +
                "Crop Type: " + sprayOrder.getCropType() + "\n" +
                "Farmland Area: " + sprayOrder.getFarmlandArea() + "\n" +
                "Desired Date: " + sprayOrder.getDesiredDate() + "\n" +
                "Desired Time: " + sprayOrder.getDesiredTime() + "\n" +
                "Total Cost: " + sprayOrder.getTotalCost() + "\n\nThank you for using our service!");
    }

    @Override
    public void sendOrderAssignedEmail(User user, SprayOrder sprayOrder, String sprayerNames) {
        sendEmail(user.getEmail(), "Spray Order Assigned", "Dear " + user.getUsername() + ",\n\nYour spray order has been assigned to the following sprayer(s):\n" +
                sprayerNames + "\n\nOrder Details:\n" +
                "Crop Type: " + sprayOrder.getCropType() + "\n" +
                "Farmland Area: " + sprayOrder.getFarmlandArea() + "\n" +
                "Desired Date: " + sprayOrder.getDesiredDate() + "\n" +
                "Desired Time: " + sprayOrder.getDesiredTime() + "\n" +
                "Total Cost: " + sprayOrder.getTotalCost() + "\n\nThank you for using our service!");
    }

    @Override
    public void sendOrderInProgressEmail(User user, SprayOrder sprayOrder) {
        sendEmail(user.getEmail(), "Spray Order In Progress", "Dear " + user.getUsername() + ",\n\nYour spray order is now in progress.\n\nOrder Details:\n" +
                "Crop Type: " + sprayOrder.getCropType() + "\n" +
                "Farmland Area: " + sprayOrder.getFarmlandArea() + "\n" +
                "Desired Date: " + sprayOrder.getDesiredDate() + "\n" +
                "Desired Time: " + sprayOrder.getDesiredTime() + "\n" +
                "Total Cost: " + sprayOrder.getTotalCost() + "\n\nThank you for using our service!");
    }

    @Override
    public void sendOrderCompletedEmail(User user, SprayOrder sprayOrder) {
        sendEmail(user.getEmail(), "Spray Order Completed", "Dear " + user.getUsername() + ",\n\nYour spray order has been completed.\n\nOrder Details:\n" +
                "Crop Type: " + sprayOrder.getCropType() + "\n" +
                "Farmland Area: " + sprayOrder.getFarmlandArea() + "\n" +
                "Desired Date: " + sprayOrder.getDesiredDate() + "\n" +
                "Desired Time: " + sprayOrder.getDesiredTime() + "\n" +
                "Total Cost: " + sprayOrder.getTotalCost() + "\n\nThank you for using our service!");
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            mailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}