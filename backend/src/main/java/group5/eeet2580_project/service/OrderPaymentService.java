package group5.eeet2580_project.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import group5.eeet2580_project.dto.request.OrderPaymentRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.dto.response.OrderPaymentResponse;
import group5.eeet2580_project.entity.OrderPayment;
import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.repository.OrderPaymentRepository;
import group5.eeet2580_project.repository.SprayOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderPaymentService {

    @Value("${stripe.api.key}")
    private String stripeAPIKey;

    private final OrderPaymentRepository orderPaymentRepository;
    private final SprayOrderRepository sprayOrderRepository;

    @Transactional
    public ResponseEntity<?> createPayment(OrderPaymentRequest request) {
        Optional<SprayOrder> sprayOrderOptional = sprayOrderRepository.findById(request.getOrderID());
        if (sprayOrderOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Order not found"));
        }

        Optional<OrderPayment> orderPaymentOptional = orderPaymentRepository.findByOrder(request.getOrderID());
        if (orderPaymentOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Payment already made for this order"));
        }

        SprayOrder sprayOrder = sprayOrderOptional.get();

        if (sprayOrder.getTotalCost() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Order total cost is 0"));
        }

        OrderPayment orderPayment = new OrderPayment();
        orderPayment.setOrder(sprayOrder);
        orderPayment.setAmount(sprayOrder.getTotalCost());

        if (request.getPaymentMethod() == OrderPayment.PaymentMethod.CARD) {
            try {
                // Set Stripe API key
                Stripe.apiKey = stripeAPIKey;

                // Create a PaymentIntent with Stripe
                PaymentIntentCreateParams params =
                        PaymentIntentCreateParams.builder()
                                .setAmount((long) (sprayOrder.getTotalCost()))
                                .setCurrency("vnd")
                                .setPaymentMethod("pm_card_visa") // Default payment method
                                .setDescription("Payment for Order ID: " + sprayOrder.getId())
                                .build();

                PaymentIntent paymentIntent = PaymentIntent.create(params);

                orderPayment.setPaymentMethod(OrderPayment.PaymentMethod.CARD);
                orderPaymentRepository.save(orderPayment);

                sprayOrder.setStatus(SprayOrder.Status.COMPLETED);
                sprayOrderRepository.save(sprayOrder);

                HashMap<String, Object> response = new HashMap<>();
                response.put("clientSecret", paymentIntent.getClientSecret());
                response.put("message", "Payment intent created successfully");

                return ResponseEntity.ok(response);

            } catch (StripeException e) {
                return ResponseEntity.badRequest().body(new MessageResponse("Stripe error: " + e.getMessage()));
            }
        }

        // Handle cash payment
        orderPayment.setPaymentMethod(request.getPaymentMethod());
        orderPaymentRepository.save(orderPayment);

        sprayOrder.setStatus(SprayOrder.Status.COMPLETED);
        sprayOrderRepository.save(sprayOrder);

        return ResponseEntity.ok(new MessageResponse("Payment successful"));
    }

    public ResponseEntity<?> getPayment(Long orderID) {
        Optional<OrderPayment> orderPaymentOptional = orderPaymentRepository.findByOrder(orderID);
        if (orderPaymentOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Payment not found"));
        }

        return ResponseEntity.ok(new OrderPaymentResponse(orderPaymentOptional.get()));
    }

    public ResponseEntity<?> getAllPayments() {
        List<OrderPayment> orderPayments = orderPaymentRepository.findAll();
        return ResponseEntity.ok(orderPayments.stream().map(OrderPaymentResponse::new));
    }
}
