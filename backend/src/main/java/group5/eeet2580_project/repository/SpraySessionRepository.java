package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.SpraySession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpraySessionRepository extends JpaRepository<SpraySession, Long> {
    @Query("SELECT S FROM SpraySession S WHERE S.order.id = :orderID")
    Optional<SpraySession> findByOrder(Long orderID);

    @Query("SELECT S FROM SpraySession S WHERE S.sprayer.username = :username")
    List<SpraySession> findBySprayer(@Param("username") String username);

    @Query("SELECT S FROM SpraySession S WHERE S.sprayer.id = :sprayerID AND S.timestamp = :timestamp")
    Optional<SpraySession> findBySprayerAndTimestamp(@Param("sprayerID") Long sprayerID, @Param("timestamp") LocalDateTime timestamp);

    @Query("SELECT CASE WHEN COUNT(S) > 0 THEN true ELSE false END FROM SpraySession S WHERE S.sprayer.id = :sprayerID AND S.timeSlot = :timeSlot")
    Boolean existsBySprayerAndTimeSlot(@Param("sprayerID") Long sprayerID, @Param("timeSlot") SpraySession.TimeSlot timeSlot);
}