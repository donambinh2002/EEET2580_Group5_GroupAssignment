package group5.eeet2580_project.service;

import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.entity.SpraySession;
import group5.eeet2580_project.entity.User;
import group5.eeet2580_project.dto.request.SprayOrderRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.repository.SprayOrderRepository;
import group5.eeet2580_project.repository.SpraySessionRepository;
import group5.eeet2580_project.repository.UserRepository;
import group5.eeet2580_project.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SprayOrderService {

    private final SprayOrderRepository sprayOrderRepository;
    private final SpraySessionRepository spraySessionRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Transactional
    public ResponseEntity<?> createSprayOrder(SprayOrderRequest request, User user) {
        // Validate request
        if (request.getFarmlandArea() <= 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid farmland area!"));
        }

        // Calculate total cost
        double totalCost = request.getFarmlandArea() * 30000;

        // Check if the desired session is available
        boolean sessionExists = spraySessionRepository.existsByDateAndTimeSlot(request.getDesiredDate(), String.valueOf(request.getDesiredTime()));
        if (!sessionExists) {
            return ResponseEntity.badRequest().body(new MessageResponse("Desired session is not available!"));
        }

        // Create and save spray order
        SprayOrder sprayOrder = SprayOrder.builder()
                .user(user)
                .cropType(request.getCropType())
                .farmLandArea(request.getFarmlandArea())
                .desiredDate(request.getDesiredDate())
                .desiredTime(request.getDesiredTime())
                .totalCost(totalCost)
                .status("Pending")
                .build();
        sprayOrderRepository.save(sprayOrder);

        // Send confirmation email
        emailService.sendOrderConfirmationEmail(user, sprayOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Spray order created successfully!"));
    }

    public List<SprayOrder> getOrdersByUser(User user) {
        return sprayOrderRepository.findByUser(user);
    }

    public List<SprayOrder> getAllOrders() {
        return sprayOrderRepository.findAll();
    }

    public List<SpraySession> getAvailableSessions(LocalDate date) {
        return spraySessionRepository.findAvailableSessionsByDate(date);
    }
}