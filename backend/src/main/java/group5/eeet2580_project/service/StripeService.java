package group5.eeet2580_project.service;

import group5.eeet2580_project.dto.request.PaymentRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.repository.SprayOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StripeService {
    private final SprayOrderRepository sprayOrderRepository;

    public ResponseEntity<?> create(PaymentRequest request) {

        return ResponseEntity.ok(new MessageResponse("Payment successful"));
    }
}
