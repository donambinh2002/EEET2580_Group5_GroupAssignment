package group5.eeet2580_project.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DeleteUserRequest {
    @NotBlank
    private String credential;
}
