package group5.eeet2580_project.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FeedbackRequest {
    @NotNull(message = "Order ID is required")
    private Long orderId;

    private String feedbackText;

    @NotNull(message = "Feedback rating is required")
    @Positive(message = "Feedback rating must be positive")
    private Integer feedbackRating;
}
