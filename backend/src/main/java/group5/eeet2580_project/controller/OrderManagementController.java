package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.request.*;
import group5.eeet2580_project.service.OrderFeedbackService;
import group5.eeet2580_project.service.OrderManagementService;
import group5.eeet2580_project.service.OrderPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/orders")
@Validated
@RequiredArgsConstructor
public class OrderManagementController {

    private final OrderManagementService orderManagementService;
    private final OrderPaymentService orderPaymentService;
    private final OrderFeedbackService orderFeedbackService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@Valid @RequestBody SprayOrderRequest request, HttpServletRequest httpRequest) {
        return orderManagementService.createOrder(request, httpRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return orderManagementService.get(id);
    }

    @GetMapping()
    public ResponseEntity<?> getOrders() {
        return orderManagementService.getAll();
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<?> confirmOrder(@PathVariable Long id, HttpServletRequest httpRequest) {
        return orderManagementService.confirmOrder(id);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id, HttpServletRequest httpRequest) {
        return orderManagementService.cancelOrder(id);
    }

    @PutMapping("/assign/{id}")
    public ResponseEntity<?> assignOrder(@PathVariable Long id, SprayerAssignRequest request, HttpServletRequest httpRequest) {
        return orderManagementService.assignOrder(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return orderManagementService.deleteOrder(id);
    }

    // Payment
    @PostMapping("/payments")
    public ResponseEntity<?> createPayment(@Valid @RequestBody OrderPaymentRequest request) {
        return orderPaymentService.createPayment(request);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<?> getPayment(@PathVariable Long id) {
        return orderPaymentService.getPayment(id);
    }

    @GetMapping("/payments")
    public ResponseEntity<?> getPayments() {
        return orderPaymentService.getAllPayments();
    }

    // Feedback
    @PostMapping("/feedbacks")
    public ResponseEntity<?> feedback(@Valid @RequestBody OrderFeedbackRequest request, HttpServletRequest httpRequest) {
        return orderFeedbackService.createFeedback(request);
    }

    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<?> getFeedback(@PathVariable Long id) {
        return orderFeedbackService.getFeedback(id);
    }

    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
        return orderFeedbackService.deleteFeedback(id);
    }

    @PutMapping("/feedbacks")
    public ResponseEntity<?> updateFeedback(@Valid @RequestBody OrderFeedbackRequest request) {
        return orderFeedbackService.updateFeedback(request);
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<?> getFeedbacks() {
        return orderFeedbackService.getAllFeedback();
    }
}