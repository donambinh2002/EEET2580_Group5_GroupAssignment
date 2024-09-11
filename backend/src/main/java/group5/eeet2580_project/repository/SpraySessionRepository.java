package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.SpraySession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpraySessionRepository extends JpaRepository<SpraySession, Long> {

    @Query("SELECT S FROM SpraySession S WHERE S.date = :date")
    List<SpraySession> findByDate(@Param("date") LocalDate date);

    @Query("SELECT S FROM SpraySession S WHERE S.date = :date AND S.isBooked = false")
    List<SpraySession> findAvailableSessionsByDate(@Param("date") LocalDate date);

    @Query("SELECT CASE WHEN COUNT(S) > 0 THEN true ELSE false END FROM SpraySession S WHERE S.date = :date AND S.timeSlot = :timeSlot")
    Boolean existsByDateAndTimeSlot(@Param("date") LocalDate date, @Param("timeSlot") String timeSlot);
}