package group5.eeet2580_project.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PaymentRequest {
    @NotNull
    @Positive
    private Long orderID;

    @NotNull
    @Positive
    private Float amount;

    @NotNull
    private String currency;

    @NotNull
    private String paymentMethod;
}
