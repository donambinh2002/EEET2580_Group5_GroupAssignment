package group5.eeet2580_project.dto.request;

import group5.eeet2580_project.entity.SprayOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SprayOrderRequest {
    private String farmer;
    
    @NotNull(message = "Desired start time is required")
    private LocalDateTime desiredStartTime;

    @NotNull(message = "Crop type is required")
    private SprayOrder.CropType cropType;

    @NotNull
    @Positive(message = "Farm land area must be positive")
    private float farmLandArea;
}