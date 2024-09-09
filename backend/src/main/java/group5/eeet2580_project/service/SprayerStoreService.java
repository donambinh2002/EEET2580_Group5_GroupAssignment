package group5.eeet2580_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import group5.eeet2580_project.dto.request.SprayerStoreRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.dto.response.SprayerStoreResponse;
import group5.eeet2580_project.entity.SprayerStore;
import group5.eeet2580_project.entity.User;
import group5.eeet2580_project.repository.SprayerStoreRepository;
import group5.eeet2580_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SprayerStoreService {
    private final SprayerStoreRepository sprayerStoreRepository;
    private final UserRepository userRepository;
    private final JedisPool jedisPool;
    private final ObjectMapper objectMapper;

    @Transactional
    public ResponseEntity<?> create(SprayerStoreRequest request) {
        User owner = userRepository.findById(request.getOwner_id()).orElse(null);
        if (owner == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }

        SprayerStore sprayerStore = SprayerStore.builder()
                .name(request.getName())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .owner(owner)
                .build();

        sprayerStoreRepository.save(sprayerStore);
        return ResponseEntity.ok(new SprayerStoreResponse(sprayerStore));
    }

    @Transactional
    public ResponseEntity<?> update(long id, SprayerStoreRequest request) {
        Optional<SprayerStore> sprayerStoreOptional = sprayerStoreRepository.findById(id);
        if (sprayerStoreOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Sprayer store not found"));
        }
        SprayerStore sprayerStore = sprayerStoreOptional.get();
        sprayerStore.setName(request.getName());
        sprayerStore.setDescription(request.getDescription());
        sprayerStore.setImageUrl(request.getImageUrl());
        sprayerStoreRepository.save(sprayerStore);
        return ResponseEntity.ok(new SprayerStoreResponse(sprayerStore));
    }

    @Transactional
    public ResponseEntity<?> delete(long id) {
        Optional<SprayerStore> sprayerStoreOptional = sprayerStoreRepository.findById(id);
        if (sprayerStoreOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Sprayer store not found"));
        }
        sprayerStoreRepository.delete(sprayerStoreOptional.get());
        return ResponseEntity.ok(new MessageResponse("Sprayer store deleted"));
    }

    public ResponseEntity<?> get(long id) {
        Optional<SprayerStore> sprayerStoreOptional = sprayerStoreRepository.findById(id);
        if (sprayerStoreOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Sprayer store not found"));
        }
        return ResponseEntity.ok(new SprayerStoreResponse(sprayerStoreOptional.get()));
    }

    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(sprayerStoreRepository.findAll());
    }

    public ResponseEntity<?> getByOwnerID(long ownerID) {
        return ResponseEntity.ok(sprayerStoreRepository.findByOwnerID(ownerID));
    }
}
