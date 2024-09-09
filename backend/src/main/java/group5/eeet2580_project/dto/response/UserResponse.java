package group5.eeet2580_project.dto.response;

import lombok.*;
import group5.eeet2580_project.entity.User;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse implements Data {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Set<String> roles;
    private String imageUrl;
    private SprayerStoreResponse sprayerStore;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.roles = user.getRoles();
        this.imageUrl = user.getImageUrl();
        if (user.getSprayerStore() != null) {
            this.sprayerStore = new SprayerStoreResponse(user.getSprayerStore());
        }
    }
}