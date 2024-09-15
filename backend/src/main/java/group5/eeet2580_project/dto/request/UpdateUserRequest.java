package group5.eeet2580_project.dto.request;

import group5.eeet2580_project.entity.User;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserRequest {
    private String fullName;

    private String address;

    private User.Expertise expertise;

    @Size(min = 6, max = 40)
    private String password;
}