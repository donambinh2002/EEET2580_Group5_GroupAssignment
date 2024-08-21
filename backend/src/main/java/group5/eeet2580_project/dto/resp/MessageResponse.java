package group5.eeet2580_project.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import group5.eeet2580_project.dto.serializer.MessageResponseSerializer;

@JsonSerialize(using = MessageResponseSerializer.class)
public class MessageResponse {
    private String message;
    private MetaData metaData;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(String message, MetaData metaData) {
        this.message = message;
        this.metaData = metaData;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}