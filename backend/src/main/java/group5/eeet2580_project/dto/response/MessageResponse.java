package group5.eeet2580_project.dto.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessageResponse {
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Data data;

    public MessageResponse(String message) {
        this.message = message;
    }
}