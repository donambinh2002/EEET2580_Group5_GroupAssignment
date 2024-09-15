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
        String subject = "Spray Order Confirmation";
        String body = buildOrderConfirmationBody(user, sprayOrder);
        sendEmail(user.getEmail(), subject, body);
    }

    @Override
    public void sendOrderCancelledEmail(User user, SprayOrder sprayOrder) {
        String subject = "Spray Order Cancelled";
        String body = buildOrderCancelledBody(user, sprayOrder);
        sendEmail(user.getEmail(), subject, body);
    }

    @Override
    public void sendOrderAssignedEmail(User user, SprayOrder sprayOrder, String sprayerNames) {
        String subject = "Spray Order Assigned";
        String body = buildOrderAssignedBody(user, sprayOrder, sprayerNames);
        sendEmail(user.getEmail(), subject, body);
    }

    @Override
    public void sendOrderInProgressEmail(User user, SprayOrder sprayOrder) {
        String subject = "Spray Order In Progress";
        String body = buildOrderInProgressBody(user, sprayOrder);
        sendEmail(user.getEmail(), subject, body);
    }

    @Override
    public void sendOrderCompletedEmail(User user, SprayOrder sprayOrder) {
        String subject = "Spray Order Completed";
        String body = buildOrderCompletedBody(user, sprayOrder);
        sendEmail(user.getEmail(), subject, body);
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
            throw new InternalError("Failed to send email", e);
        }
    }

    // Utility methods to build the email body for different scenarios
    private String buildOrderConfirmationBody(User user, SprayOrder sprayOrder) {
        return "Dear " + user.getUsername() + ",\n\nYour spray order has been created successfully.\n\n" +
                buildOrderDetails(sprayOrder) +
                "\nThank you for using our service!";
    }

    private String buildOrderCancelledBody(User user, SprayOrder sprayOrder) {
        return "Dear " + user.getUsername() + ",\n\nYour spray order has been cancelled.\n\n" +
                buildOrderDetails(sprayOrder) +
                "\nThank you for using our service!";
    }

    private String buildOrderAssignedBody(User user, SprayOrder sprayOrder, String sprayerNames) {
        return "Dear " + user.getUsername() + ",\n\nYour spray order has been assigned to the following sprayer(s):\n" +
                sprayerNames + "\n\n" +
                buildOrderDetails(sprayOrder) +
                "\nThank you for using our service!";
    }

    private String buildOrderInProgressBody(User user, SprayOrder sprayOrder) {
        return "Dear " + user.getUsername() + ",\n\nYour spray order is now in progress.\n\n" +
                buildOrderDetails(sprayOrder) +
                "\nThank you for using our service!";
    }

    private String buildOrderCompletedBody(User user, SprayOrder sprayOrder) {
        return "Dear " + user.getUsername() + ",\n\nYour spray order has been completed.\n\n" +
                buildOrderDetails(sprayOrder) +
                "\nThank you for using our service!";
    }

    // Common method for order details
    private String buildOrderDetails(SprayOrder sprayOrder) {
        return "Order ID: " + sprayOrder.getId() + "\n" +
                "Total Cost: " + sprayOrder.getTotalCost() + "\n";
    }
}
