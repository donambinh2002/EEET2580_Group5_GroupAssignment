package group5.eeet2580_project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SprayOrderRequest {
    private String sprayerID;
    private String cropType;
    private double farmlandArea;
    private LocalDate desiredDate;
    private LocalTime desiredTime;
}