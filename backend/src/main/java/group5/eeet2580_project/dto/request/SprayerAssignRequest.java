package group5.eeet2580_project.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SprayerAssignRequest {
    @NotNull(message = "Order ID is required")
    @Positive(message = "Order ID must be positive")
    private Long orderID;

    @NotNull(message = "Sprayer username is required")
    private String sprayer;
}
