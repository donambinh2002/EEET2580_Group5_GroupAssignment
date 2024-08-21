package group5.eeet2580_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginUserRequest {
    @NotBlank
    private String credential; // This can be either username or email

    @NotBlank
    private String password;
}