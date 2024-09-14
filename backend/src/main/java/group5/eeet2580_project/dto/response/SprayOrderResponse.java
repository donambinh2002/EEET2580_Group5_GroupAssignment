package group5.eeet2580_project.dto.response;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayOrderResponse implements Data {
    private Long id;
    private UserResponse farmer;
    private LocalDate orderDate;
    private String status;
}