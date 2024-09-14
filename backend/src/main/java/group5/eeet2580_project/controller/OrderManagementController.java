package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.request.*;
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

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@Valid @RequestBody SprayOrderRequest request, HttpServletRequest httpRequest) {
        return orderManagementService.createOrder(request, httpRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return orderManagementService.get(id);
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
    public ResponseEntity<?> assignOrder(@PathVariable Long id, @RequestParam String sprayer, HttpServletRequest httpRequest) {
        return orderManagementService.assignOrder(id, sprayer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return orderManagementService.deleteOrder(id);
    }

    @PostMapping("/feedback")
    public ResponseEntity<?> feedback(@Valid @RequestBody FeedbackRequest request, HttpServletRequest httpRequest) {
        return orderManagementService.orderFeedback(request);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> createPayment(@Valid @RequestBody OrderPaymentRequest request) {
        return orderPaymentService.createPayment(request);
    }
}