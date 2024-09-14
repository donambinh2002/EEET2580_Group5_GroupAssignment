package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.response.SpraySessionResponse;
import group5.eeet2580_project.entity.SpraySession;
import group5.eeet2580_project.service.SpraySessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/spray-sessions")
@Validated
@RequiredArgsConstructor
public class SpraySessionController {

    private final SpraySessionService spraySessionService;

//    @GetMapping("/available")
//    public ResponseEntity<List<SpraySessionResponse>> getAvailableSessions(@RequestParam LocalDate date) {
//        List<SpraySession> sessions = spraySessionService.getAvailableSessions(date);
//        List<SpraySessionResponse> response = sessions.stream()
//                .map(session -> SpraySessionResponse.builder()
//                        .id(session.getId())
//                        .build())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(response);
//    }
}