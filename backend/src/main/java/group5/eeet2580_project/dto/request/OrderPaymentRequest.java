package group5.eeet2580_project.dto.request;

import group5.eeet2580_project.entity.OrderPayment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderPaymentRequest {
    @NotNull
    @Positive
    private Long orderID;

    @NotNull
    private OrderPayment.PaymentMethod paymentMethod;

    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
}
