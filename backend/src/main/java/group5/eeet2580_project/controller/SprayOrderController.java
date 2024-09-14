package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.request.FeedbackRequest;
import group5.eeet2580_project.dto.response.SprayOrderResponse;
import group5.eeet2580_project.dto.response.UserResponse;
import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.service.SprayOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/spray-orders")
@Validated
@RequiredArgsConstructor
public class SprayOrderController {
    private final SprayOrderService sprayOrderService;

    @GetMapping
    public ResponseEntity<List<SprayOrderResponse>> getAllOrders() {
        List<SprayOrder> orders = sprayOrderService.getAllOrders();
        List<SprayOrderResponse> response = orders.stream()
                .map(order -> SprayOrderResponse.builder()
                        .id(order.getId())
                        .farmer(new UserResponse(order.getFarmer()))
//                        .orderDate(order.getDesiredDate())
                        .status(String.valueOf(order.getStatus()))
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}/status")
    public void updateOrderStatus(@PathVariable Long orderId, @RequestParam SprayOrder.Status status, @RequestParam(required = false) String sprayerNames) {
        sprayOrderService.updateOrderStatus(orderId, status, sprayerNames);
    }

    @PutMapping("/{orderId}/feedback")
    public ResponseEntity<?> submitFeedback(@PathVariable Long orderId, @RequestBody FeedbackRequest feedbackRequest) {
        sprayOrderService.submitFeedback(orderId, feedbackRequest);
        return ResponseEntity.ok().body("Feedback submitted successfully!");
    }
}