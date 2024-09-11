package group5.eeet2580_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import group5.eeet2580_project.dto.request.SprayerRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.dto.response.SprayerResponse;
import group5.eeet2580_project.entity.Sprayer;
import group5.eeet2580_project.repository.SprayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SprayerService {
    private final SprayerRepository sprayerRepository;
    private final JedisPool jedisPool;
    private final ObjectMapper objectMapper;

    @Transactional
    public ResponseEntity<?> create(SprayerRequest request) {
        Sprayer sprayer = Sprayer.builder()
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .cropType(request.getCropType())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .build();

        sprayerRepository.save(sprayer);
        return ResponseEntity.ok(new SprayerResponse(sprayer));
    }

    @Transactional
    public ResponseEntity<?> update(long id, SprayerRequest request) {
        Optional<Sprayer> sprayerOptional = sprayerRepository.findById(id);
        if (sprayerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Sprayer not found"));
        }
        Sprayer sprayer = sprayerOptional.get();
        sprayer.setQuantity(request.getQuantity());
        sprayer.setPrice(request.getPrice());
        sprayer.setCropType(request.getCropType());
        sprayer.setDescription(request.getDescription());
        sprayer.setImageUrl(request.getImageUrl());
        sprayerRepository.save(sprayer);
        return ResponseEntity.ok(new SprayerResponse(sprayer));
    }

    @Transactional
    public ResponseEntity<?> delete(long id) {
        Optional<Sprayer> sprayerOptional = sprayerRepository.findById(id);
        if (sprayerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Sprayer not found"));
        }
        Sprayer sprayer = sprayerOptional.get();
        sprayerRepository.delete(sprayer);
        return ResponseEntity.ok(new MessageResponse("Sprayer deleted successfully"));
    }

    public ResponseEntity<?> get(long id) {
        Optional<Sprayer> sprayerOptional = sprayerRepository.findById(id);
        if (sprayerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Sprayer not found"));
        }
        Sprayer sprayer = sprayerOptional.get();
        return ResponseEntity.ok(new SprayerResponse(sprayer));
    }

    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(sprayerRepository.findAll());
    }
}
