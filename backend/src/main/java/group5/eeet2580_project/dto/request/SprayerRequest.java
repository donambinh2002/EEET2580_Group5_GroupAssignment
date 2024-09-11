package group5.eeet2580_project.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayerRequest {
    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Float price;

    private String cropType;

    private String description;

    private String imageUrl;
}
