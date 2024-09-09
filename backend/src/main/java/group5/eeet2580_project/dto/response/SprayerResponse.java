package group5.eeet2580_project.dto.response;

import group5.eeet2580_project.entity.Sprayer;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayerResponse implements Data {
    private Long id;

    private Integer quantity;

    private Float price;

    private String cropType;

    private String description;

    private String imageUrl;

    private SprayerStoreResponse sprayerStore;

    public SprayerResponse(Sprayer sprayer) {
        this.id = sprayer.getId();
        this.quantity = sprayer.getQuantity();
        this.price = sprayer.getPrice();
        this.cropType = sprayer.getCropType();
        this.description = sprayer.getDescription();
        this.imageUrl = sprayer.getImageUrl();
        this.sprayerStore = new SprayerStoreResponse(sprayer.getSprayerStore());
    }
}
