package group5.eeet2580_project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class SprayOrderRequest {
    private String cropType;
    private double farmlandArea;
    private LocalDate desiredDate;
    private LocalTime desiredTime;
}