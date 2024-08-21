package group5.eeet2580_project.dto.resp;

import lombok.*;
import group5.eeet2580_project.entity.User;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse implements MetaData {
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}