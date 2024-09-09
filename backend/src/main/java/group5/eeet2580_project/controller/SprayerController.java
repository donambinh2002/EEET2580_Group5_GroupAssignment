package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.request.SprayerRequest;
import group5.eeet2580_project.service.SprayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/sprayers")
@Validated
@RequiredArgsConstructor
public class SprayerController {

    private final SprayerService sprayerService;

    @PostMapping()
    public ResponseEntity<?> addSprayer(@Valid @RequestBody SprayerRequest request) {
        return sprayerService.create(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSprayer(@PathVariable Long id, @Valid @RequestBody SprayerRequest request) {
        return sprayerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSprayer(@PathVariable Long id) {
        return sprayerService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSprayerById(@PathVariable Long id) {
        return sprayerService.get(id);
    }

    @GetMapping("/store/{storeID}")
    public ResponseEntity<?> getSprayersByStoreId(@PathVariable Long storeID) {
        return sprayerService.getByStoreID(storeID);
    }

    @GetMapping()
    public ResponseEntity<?> getAllSprayers() {
        return sprayerService.getAll();
    }
}