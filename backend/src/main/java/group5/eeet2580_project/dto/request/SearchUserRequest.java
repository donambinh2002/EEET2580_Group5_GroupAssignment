package group5.eeet2580_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SearchUserRequest {
    @NotBlank
    private String credential;
}
