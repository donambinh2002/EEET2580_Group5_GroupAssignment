package group5.eeet2580_project.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderFeedbackRequest {
    @NotNull(message = "Order ID is required")
    private Long orderID;

    private String text;

    @NotNull(message = "Feedback rating is required")
    @Positive(message = "Feedback rating must be positive")
    private Integer rating;

    @NotNull(message = "Attentive rating is required")
    @Positive(message = "Attentive rating must be positive")
    private Integer attentiveRating;

    @NotNull(message = "Friendly rating is required")
    @Positive(message = "Friendly rating must be positive")
    private Integer friendlyRating;

    @NotNull(message = "Professional rating is required")
    @Positive(message = "Professional rating must be positive")
    private Integer professionalRating;
}
