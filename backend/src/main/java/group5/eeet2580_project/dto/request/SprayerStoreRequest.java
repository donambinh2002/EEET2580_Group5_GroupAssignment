package group5.eeet2580_project.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayerStoreRequest {
    @NotNull
    private Long owner_id;

    @NotNull
    private String name;

    private String description;

    private String imageUrl;
}
