package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.OrderFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderFeedbackRepository extends JpaRepository<OrderFeedback, Long> {
    @Query("SELECT S FROM OrderPayment S WHERE S.order.id = :orderID")
    Optional<OrderFeedback> findByOrder(Long orderID);
}