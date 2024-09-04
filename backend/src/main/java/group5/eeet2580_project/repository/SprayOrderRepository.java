package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.SprayOrder;
import group5.eeet2580_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SprayOrderRepository extends JpaRepository<SprayOrder, Long> {

    @Query("SELECT S FROM SprayOrder S WHERE S.cropType = :cropType")
    List<SprayOrder> findByCropType(@Param("cropType") String cropType);

    @Query("SELECT S FROM SprayOrder S WHERE S.farmLandArea > :area")
    List<SprayOrder> findByFarmlandAreaGreaterThan(@Param("area") double area);

    @Query("SELECT S FROM SprayOrder S WHERE S.desiredDate = :date")
    List<SprayOrder> findByDesiredDate(@Param("date") LocalDate date);

    @Query("SELECT CASE WHEN COUNT(S) > 0 THEN true ELSE false END FROM SprayOrder S WHERE S.user = :user AND S.desiredDate = :date")
    Boolean existsByUserAndDate(@Param("user") User user, @Param("date") LocalDate date);

    List<SprayOrder> findByUser(User user);
}
