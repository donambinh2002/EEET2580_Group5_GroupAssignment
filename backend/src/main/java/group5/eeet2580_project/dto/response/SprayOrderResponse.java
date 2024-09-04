package group5.eeet2580_project.dto.response;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayOrderResponse {
    private Long id;
    private String customerName;
    private LocalDate orderDate;
    private String status;
}