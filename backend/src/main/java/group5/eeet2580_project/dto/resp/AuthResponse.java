package group5.eeet2580_project.dto.resp;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse implements MetaData {
    private String accessToken;
    private String refreshToken;
}
