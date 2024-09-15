// src/main/java/group5/eeet2580_project/controller/EmailController.java
package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.request.EmailRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.entity.User;
import group5.eeet2580_project.repository.SprayOrderRepository;
import group5.eeet2580_project.repository.UserRepository;
import group5.eeet2580_project.service.email.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SprayOrderRepository sprayOrderRepository;

    @PostMapping("/send-order-confirmation")
    public ResponseEntity<MessageResponse> sendOrderConfirmationEmail(@RequestBody EmailRequest emailRequest) {
        User user = getUserFromRequest(emailRequest);
        SprayOrder sprayOrder = getSprayOrderFromRequest(emailRequest);
        emailService.sendOrderConfirmationEmail(user, sprayOrder);
        return ResponseEntity.ok(new MessageResponse("Order confirmation email sent successfully!"));
    }

    @PostMapping("/send-order-cancelled")
    public ResponseEntity<MessageResponse> sendOrderCancelledEmail(@RequestBody EmailRequest emailRequest) {
        User user = getUserFromRequest(emailRequest);
        SprayOrder sprayOrder = getSprayOrderFromRequest(emailRequest);
        emailService.sendOrderCancelledEmail(user, sprayOrder);
        return ResponseEntity.ok(new MessageResponse("Order cancelled email sent successfully!"));
    }

    @PostMapping("/send-order-assigned")
    public ResponseEntity<MessageResponse> sendOrderAssignedEmail(@RequestBody EmailRequest emailRequest, @RequestParam String sprayerNames) {
        User user = getUserFromRequest(emailRequest);
        SprayOrder sprayOrder = getSprayOrderFromRequest(emailRequest);
        emailService.sendOrderAssignedEmail(user, sprayOrder, sprayerNames);
        return ResponseEntity.ok(new MessageResponse("Order assigned email sent successfully!"));
    }

    @PostMapping("/send-order-in-progress")
    public ResponseEntity<MessageResponse> sendOrderInProgressEmail(@RequestBody EmailRequest emailRequest) {
        User user = getUserFromRequest(emailRequest);
        SprayOrder sprayOrder = getSprayOrderFromRequest(emailRequest);
        emailService.sendOrderInProgressEmail(user, sprayOrder);
        return ResponseEntity.ok(new MessageResponse("Order in progress email sent successfully!"));
    }

    @PostMapping("/send-order-completed")
    public ResponseEntity<MessageResponse> sendOrderCompletedEmail(@RequestBody EmailRequest emailRequest) {
        User user = getUserFromRequest(emailRequest);
        SprayOrder sprayOrder = getSprayOrderFromRequest(emailRequest);
        emailService.sendOrderCompletedEmail(user, sprayOrder);
        return ResponseEntity.ok(new MessageResponse("Order completed email sent successfully!"));
    }

    private User getUserFromRequest(EmailRequest emailRequest) {
        Optional<User> userOptional = userRepository.findByEmail(emailRequest.getTo());
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User not found with email: " + emailRequest.getTo());
        }
    }

    private SprayOrder getSprayOrderFromRequest(EmailRequest emailRequest) {
        Optional<SprayOrder> sprayOrderOptional = sprayOrderRepository.findByOrderNumber(Long.valueOf(emailRequest.getSubject()));
        return sprayOrderOptional.orElseThrow(() -> new EntityNotFoundException("Spray order not found with order number: " + emailRequest.getSubject()));
    }
}