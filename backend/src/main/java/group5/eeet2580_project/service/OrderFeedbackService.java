package group5.eeet2580_project.service;

import group5.eeet2580_project.dto.request.OrderFeedbackRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.dto.response.OrderFeedbackResponse;
import group5.eeet2580_project.entity.OrderFeedback;
import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.repository.OrderFeedbackRepository;
import group5.eeet2580_project.repository.SprayOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderFeedbackService {

    private final OrderFeedbackRepository orderFeedbackRepository;
    private final SprayOrderRepository sprayOrderRepository;

    @Transactional
    public ResponseEntity<?> createFeedback(OrderFeedbackRequest request) {
        Optional<SprayOrder> sprayOrderOptional = sprayOrderRepository.findById(request.getOrderID());
        if (sprayOrderOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Order not found"));
        }

        Optional<OrderFeedback> orderFeedbackOptional = orderFeedbackRepository.findByOrder(request.getOrderID());
        if (orderFeedbackOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Feedback already made for this order"));
        }

        SprayOrder sprayOrder = sprayOrderOptional.get();
        OrderFeedback orderFeedback = new OrderFeedback();
        orderFeedback.setOrder(sprayOrder);
        orderFeedback.setRating(request.getRating());
        orderFeedback.setText(request.getText());
        orderFeedback.setAttentiveRating(request.getAttentiveRating());
        orderFeedback.setFriendlyRating(request.getFriendlyRating());
        orderFeedback.setProfessionalRating(request.getProfessionalRating());

        orderFeedbackRepository.save(orderFeedback);

        return ResponseEntity.ok(new MessageResponse("Feedback for order " + request.getOrderID() + " received"));
    }

    public ResponseEntity<?> getFeedback(Long orderID) {
        Optional<OrderFeedback> orderFeedbackOptional = orderFeedbackRepository.findByOrder(orderID);
        if (orderFeedbackOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Feedback not found"));
        }

        return ResponseEntity.ok(new OrderFeedbackResponse(orderFeedbackOptional.get()));
    }

    @Transactional
    public ResponseEntity<?> deleteFeedback(Long orderID) {
        Optional<OrderFeedback> orderFeedbackOptional = orderFeedbackRepository.findByOrder(orderID);
        if (orderFeedbackOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Feedback not found"));
        }

        orderFeedbackRepository.deleteById(orderFeedbackOptional.get().getId());

        return ResponseEntity.ok(new MessageResponse("Feedback for order " + orderID + " deleted"));
    }

    @Transactional
    public ResponseEntity<?> updateFeedback(OrderFeedbackRequest request) {
        Optional<OrderFeedback> orderFeedbackOptional = orderFeedbackRepository.findByOrder(request.getOrderID());
        if (orderFeedbackOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Feedback not found"));
        }

        OrderFeedback orderFeedback = orderFeedbackOptional.get();
        orderFeedback.setRating(request.getRating());
        orderFeedback.setText(request.getText());
        orderFeedback.setAttentiveRating(request.getAttentiveRating());
        orderFeedback.setFriendlyRating(request.getFriendlyRating());
        orderFeedback.setProfessionalRating(request.getProfessionalRating());

        orderFeedbackRepository.save(orderFeedback);

        return ResponseEntity.ok(new MessageResponse("Feedback for order " + request.getOrderID() + " updated"));
    }

    public ResponseEntity<?> getAllFeedback() {
        List<OrderFeedback> orderFeedbacks = orderFeedbackRepository.findAll();
        return ResponseEntity.ok(orderFeedbacks.stream().map(OrderFeedbackResponse::new));
    }
}
