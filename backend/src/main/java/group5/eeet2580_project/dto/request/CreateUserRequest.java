package group5.eeet2580_project.dto.request;

import group5.eeet2580_project.common.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateUserRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private String fullName;

    private String address;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Pattern(regexp = Constants.ROLE_KEYS.ROLE_REGEX, message = "Invalid role, try FARMER, RECEPTIONIST or SPRAYER")
    private String role;
}