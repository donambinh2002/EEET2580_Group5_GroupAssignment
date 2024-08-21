package group5.eeet2580_project.dto.req;

import jakarta.validation.constraints.NotBlank;

public class SearchUserRequest {
    @NotBlank
    private String credential;

    // Getters and Setters
    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
