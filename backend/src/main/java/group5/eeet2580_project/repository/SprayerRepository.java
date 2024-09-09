package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.Sprayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SprayerRepository extends JpaRepository<Sprayer, Long> {
    @Query("SELECT s FROM Sprayer s WHERE s.sprayerStore.id = :id")
    Sprayer findBySprayerStoreID(@Param("storeID") Long id);
}
