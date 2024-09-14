package group5.eeet2580_project.dto.response;

import lombok.*;
import group5.eeet2580_project.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private String address;
    private User.Expertise expertise;
    private Set<String> roles;
    private String imageUrl;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.address = user.getAddress();
        this.expertise = user.getExpertise();
        this.roles = user.getRoles();
        this.imageUrl = user.getImageUrl();
    }

    public static List<UserResponse> fromList(List<User> users) {
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }
}