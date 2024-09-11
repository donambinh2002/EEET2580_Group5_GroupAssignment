package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SprayOrderRepository extends JpaRepository<SprayOrder, Long> {
    @Query("SELECT CASE WHEN COUNT(S) > 0 THEN true ELSE false END FROM SprayOrder S WHERE S.user.username = :username AND S.timestamp = :timestamp")
    Boolean existsByUserAndDate(@Param("username") String username, @Param("timestamp") LocalDateTime timestamp);

    @Query("SELECT S FROM SprayOrder S WHERE S.user.username = :username")
    List<SprayOrder> findByUser(@Param("username") String username);
}
