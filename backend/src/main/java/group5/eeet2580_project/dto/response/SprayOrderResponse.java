package group5.eeet2580_project.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayOrderResponse implements Data {
    private Long id;
    private UserResponse farmer;
    private LocalDateTime timestamp;
    private String status;
}