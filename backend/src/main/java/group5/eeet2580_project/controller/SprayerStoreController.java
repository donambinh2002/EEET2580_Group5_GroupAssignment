package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.request.SprayerStoreRequest;
import group5.eeet2580_project.service.SprayerStoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/sprayers")
@Validated
@RequiredArgsConstructor
public class SprayerStoreController {

    private final SprayerStoreService sprayerStoreService;

    @PostMapping()
    public ResponseEntity<?> addSprayerStore(@Valid @RequestBody SprayerStoreRequest request) {
        return sprayerStoreService.create(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSprayerStore(@PathVariable Long id, @Valid @RequestBody SprayerStoreRequest request) {
        return sprayerStoreService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSprayerStore(@PathVariable Long id) {
        return sprayerStoreService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSprayerStoreById(@PathVariable Long id) {
        return sprayerStoreService.get(id);
    }

    @GetMapping("/owner/{ownerID}")
    public ResponseEntity<?> getSprayersByStoreId(@PathVariable Long ownerID) {
        return sprayerStoreService.getByOwnerID(ownerID);
    }

    @GetMapping()
    public ResponseEntity<?> getAllSprayerStores() {
        return sprayerStoreService.getAll();
    }
}