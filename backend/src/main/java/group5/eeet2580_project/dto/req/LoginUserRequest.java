package group5.eeet2580_project.dto.req;

import jakarta.validation.constraints.NotBlank;

public class LoginUserRequest {
    @NotBlank
    private String credential; // This can be either username or email

    @NotBlank
    private String password;

    // Getters and Setters
    public String getCredential() {
        return credential;
    }

    public void setCredential(String login) {
        this.credential = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}