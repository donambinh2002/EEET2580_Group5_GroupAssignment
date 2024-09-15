package group5.eeet2580_project.dto.response;

import group5.eeet2580_project.entity.OrderPayment;
import group5.eeet2580_project.entity.SprayOrder;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderPaymentResponse implements Data {
    private Long id;
    private Long orderID;
    private double amount;
    private String paymentMethod;

    public OrderPaymentResponse(OrderPayment orderPayment) {
        this.id = orderPayment.getId();
        this.orderID = orderPayment.getOrder().getId();
        this.amount = orderPayment.getAmount();
        this.paymentMethod = orderPayment.getPaymentMethod().name();
    }
}