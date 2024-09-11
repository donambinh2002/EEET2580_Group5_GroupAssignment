package group5.eeet2580_project.dto.response;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SpraySessionResponse implements Data {
    private Long id;
    private LocalDate date;
    private String timeSlot;
    private String status;
}