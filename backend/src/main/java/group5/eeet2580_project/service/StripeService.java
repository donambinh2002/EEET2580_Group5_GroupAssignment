package group5.eeet2580_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import group5.eeet2580_project.dto.request.PaymentRequest;
import group5.eeet2580_project.dto.request.SprayerRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.dto.response.SprayerResponse;
import group5.eeet2580_project.entity.Sprayer;
import group5.eeet2580_project.entity.SprayerStore;
import group5.eeet2580_project.repository.SprayOrderRepository;
import group5.eeet2580_project.repository.SprayerRepository;
import group5.eeet2580_project.repository.SprayerStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StripeService {
    private final SprayOrderRepository sprayOrderRepository;

    public ResponseEntity<?> create(PaymentRequest request) {

        return ResponseEntity.ok(new MessageResponse("Payment successful"));
    }
}
