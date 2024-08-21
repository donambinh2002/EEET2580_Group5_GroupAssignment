package group5.eeet2580_project.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessageResponse {
    private String message;
    private MetaData metaData;

    public MessageResponse(String message) {
        this.message = message;
    }
}