package group5.eeet2580_project.dto.response;

import group5.eeet2580_project.entity.SprayOrder;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayOrderResponse implements Data {
    private Long id;
    private float farmLandArea;
    private SprayOrder.CropType cropType;
    private double totalCost;
    private LocalDateTime orderTime;
    private LocalDateTime desiredTime;
    private String status;
    private String farmerUsername;
    private SpraySessionResponse session;

    public SprayOrderResponse(SprayOrder sprayOrder) {
        this.id = sprayOrder.getId();
        this.farmLandArea = sprayOrder.getFarmLandArea();
        this.cropType = sprayOrder.getCropType();
        this.totalCost = sprayOrder.getTotalCost();
        this.orderTime = sprayOrder.getOrderTime();
        this.desiredTime = sprayOrder.getDesiredStartTime();
        this.status = sprayOrder.getStatus().name();
        this.farmerUsername = sprayOrder.getFarmer().getUsername();
        this.session = sprayOrder.getSpraySession() == null ? null : new SpraySessionResponse(sprayOrder.getSpraySession());
    }
}