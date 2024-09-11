package group5.eeet2580_project.service;

import group5.eeet2580_project.entity.SpraySession;
import group5.eeet2580_project.repository.SpraySessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpraySessionService {
    private final SpraySessionRepository spraySessionRepository;

    public List<SpraySession> getAvailableSessions(LocalDate date) {
        return spraySessionRepository.findAvailableSessionsByDate(date);
    }
}