package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.SpraySession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SpraySessionRepository extends JpaRepository<SpraySession, Long> {
    @Query("SELECT S FROM SpraySession S WHERE S.order.id = :orderID")
    Optional<SpraySession> findByOrder(Long orderID);

    @Query("SELECT CASE WHEN COUNT(S) > 1 THEN TRUE ELSE FALSE END FROM SpraySession S WHERE S.date = :date AND S.timeSlot = :timeSlot")
    boolean sessionFilled(LocalDate date, SpraySession.TimeSlot timeSlot);
}