package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.SprayOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprayOrderRepository extends JpaRepository<SprayOrder, Long> {
    @Query("SELECT S FROM SprayOrder S WHERE S.farmer.username = :username")
    List<SprayOrder> findByFarmer(@Param("username") String username);

    @Query("SELECT S FROM SprayOrder S JOIN S.spraySession.sprayers sprayer WHERE sprayer.username = :username")
    List<SprayOrder> findBySprayer(@Param("username") String username);
}
