package group5.eeet2580_project.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import group5.eeet2580_project.dto.serializer.MessageResponseSerializer;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonSerialize(using = MessageResponseSerializer.class)
public class MessageResponse {
    private String message;
    private MetaData metaData;

    public MessageResponse(String message) {
        this.message = message;
    }
}