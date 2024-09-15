package group5.eeet2580_project.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SprayerAssignRequest {
    @NotNull(message = "Sprayer username is required")
    private String sprayer;
}
