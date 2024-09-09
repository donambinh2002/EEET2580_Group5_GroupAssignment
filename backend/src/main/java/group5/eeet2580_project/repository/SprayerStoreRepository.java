package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.SprayerStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SprayerStoreRepository extends JpaRepository<SprayerStore, Long> {
    @Query("SELECT s FROM SprayerStore s WHERE s.owner.id = :id")
    SprayerStore findByOwnerID(Long id);
}
