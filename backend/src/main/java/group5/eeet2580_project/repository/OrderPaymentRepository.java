package group5.eeet2580_project.repository;

import group5.eeet2580_project.entity.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {
    @Query("SELECT S FROM OrderPayment S WHERE S.order.id = :orderID")
    Optional<OrderPayment> findByOrder(Long orderID);
}