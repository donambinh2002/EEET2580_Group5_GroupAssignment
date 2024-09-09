package group5.eeet2580_project.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse implements Data {
    private String accessToken;
    private String refreshToken;
}
