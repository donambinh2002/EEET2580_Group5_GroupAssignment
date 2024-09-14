package group5.eeet2580_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import group5.eeet2580_project.common.Constants;
import group5.eeet2580_project.dto.request.FeedbackRequest;
import group5.eeet2580_project.dto.request.SprayOrderRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.dto.response.SprayOrderResponse;
import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.entity.SpraySession;
import group5.eeet2580_project.entity.User;
import group5.eeet2580_project.repository.SprayOrderRepository;
import group5.eeet2580_project.repository.SpraySessionRepository;
import group5.eeet2580_project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final SprayOrderRepository sprayOrderRepository;
    private final SpraySessionRepository spraySessionRepository;
    private final UserRepository userRepository;
    private final JedisPool jedisPool;
    private final ObjectMapper objectMapper;


    public ResponseEntity<?> createOrder(SprayOrderRequest request, HttpServletRequest httpRequest) {
        SpraySession.TimeSlot timeSlot = SpraySession.getTimeSlot(request.getDesiredStartTime());

        if (spraySessionRepository.sessionFilled(request.getDesiredStartTime().toLocalDate(), timeSlot)) {
            return ResponseEntity.badRequest().body(new MessageResponse("No available session at this time slot!"));
        }

        String token = httpRequest.getHeader("Authorization").substring(7);
        Optional<User> farmerOptional;

        try (Jedis jedis = jedisPool.getResource()) {
            String cachedUser = jedis.get("user:" + token);
            if (cachedUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("User not logged in"));
            }

            User user = objectMapper.readValue(cachedUser, User.class);

            if (user.getRoles().contains(Constants.ROLE_KEYS.FARMER)) {
                farmerOptional = userRepository.findByUsername(user.getUsername());
            } else if (user.getRoles().contains(Constants.ROLE_KEYS.RECEPTIONIST)) {
                if (request.getFarmerUsername() == null) {
                    return ResponseEntity.badRequest().body(new MessageResponse("Farmer username is required"));
                }
                farmerOptional = userRepository.findByUsername(request.getFarmerUsername());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("User not authorized to create order"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Unable to verify user"));
        }

        if (farmerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Farmer not found!"));
        }

        SprayOrder sprayOrder = SprayOrder.builder()
                .farmer(farmerOptional.get())
                .farmLandArea(request.getFarmLandArea())
                .desiredStartTime(request.getDesiredStartTime())
                .orderTime(LocalDateTime.now())
                .status(SprayOrder.Status.PENDING)
                .build();

        sprayOrderRepository.save(sprayOrder);

        return ResponseEntity.ok(new MessageResponse("Order" + sprayOrder.getId() + " created successfully!"));
    }

    public ResponseEntity<?> confirmOrder(Long id) {
        Optional<SprayOrder> orderOptional = sprayOrderRepository.findById(id);

        if (orderOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Order not found!"));
        }

        SprayOrder order = orderOptional.get();
        order.setStatus(SprayOrder.Status.CONFIRMED);
        sprayOrderRepository.save(order);

        return ResponseEntity.ok(new MessageResponse("Order " + id + " confirmed successfully!"));
    }

    public ResponseEntity<?> cancelOrder(Long id) {
        Optional<SprayOrder> orderOptional = sprayOrderRepository.findById(id);

        if (orderOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Order not found!"));
        }

        SprayOrder order = orderOptional.get();
        order.setStatus(SprayOrder.Status.CANCELLED);
        sprayOrderRepository.save(order);

        return ResponseEntity.ok(new MessageResponse("Order " + id + " cancelled successfully!"));
    }

    public ResponseEntity<?> assignOrder(Long id, String sprayerUsername) {
        Optional<SprayOrder> orderOptional = sprayOrderRepository.findById(id);

        if (orderOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Order not found!"));
        }

        Optional<User> sprayerOptional = userRepository.findByUsername(sprayerUsername);

        if (sprayerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Sprayer not found!"));
        }

        SprayOrder order = orderOptional.get();

        SpraySession spraySession = spraySessionRepository.findByOrder(id).orElse(null);
        if (spraySession != null) {
            spraySession.getSprayers().add(sprayerOptional.get());
        } else {
            spraySession = SpraySession.builder()
                    .sprayers(new ArrayList<>(List.of(sprayerOptional.get())))
                    .date(order.getDesiredStartTime().toLocalDate())
                    .timeSlot(SpraySession.getTimeSlot(order.getDesiredStartTime()))
                    .build();

            order.setSpraySession(spraySession);
        }

        order.setStatus(SprayOrder.Status.ASSIGNED);

        spraySessionRepository.save(spraySession);
        sprayOrderRepository.save(order);

        return ResponseEntity.ok(new MessageResponse("Order " + id + " assigned to sprayer: " + sprayerUsername + " successfully!"));
    }

    public ResponseEntity<?> get(Long id) {
        Optional<SprayOrder> orderOptional = sprayOrderRepository.findById(id);

        if (orderOptional.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Order not found!"));
        }

        return ResponseEntity.ok(new SprayOrderResponse(orderOptional.get()));
    }

    @Transactional
    public ResponseEntity<?> deleteOrder(Long id) {
        Optional<SprayOrder> orderOptional = sprayOrderRepository.findById(id);

        if (orderOptional.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Order not found!"));
        }

        sprayOrderRepository.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("Order " + id + " deleted successfully!"));
    }

    @Transactional
    public ResponseEntity<?> orderFeedback(FeedbackRequest request) {
        Optional<SprayOrder> orderOptional = sprayOrderRepository.findById(request.getOrderId());

        if (orderOptional.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Order not found!"));
        }

        SprayOrder order = orderOptional.get();
        order.setFeedback(request.getFeedbackText(), request.getFeedbackRating());
        sprayOrderRepository.save(order);

        return ResponseEntity.ok(new MessageResponse("Feedback for order " + request.getOrderId() + " submitted successfully!"));
    }
}
