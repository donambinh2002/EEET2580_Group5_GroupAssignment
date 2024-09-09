package group5.eeet2580_project.dto.response;

import group5.eeet2580_project.entity.SprayerStore;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayerStoreResponse implements Data {
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private UserResponse owner;

    private List<SprayerResponse> sprayers;

    public SprayerStoreResponse(SprayerStore sprayerStore) {
        this.id = sprayerStore.getId();
        this.name = sprayerStore.getName();
        this.description = sprayerStore.getDescription();
        this.imageUrl = sprayerStore.getImageUrl();
        this.owner = new UserResponse(sprayerStore.getOwner());
        this.sprayers = sprayerStore.getSprayers().stream().map(SprayerResponse::new).collect(Collectors.toList());
    }
}
