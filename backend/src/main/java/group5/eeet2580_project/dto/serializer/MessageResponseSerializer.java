package group5.eeet2580_project.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import group5.eeet2580_project.dto.resp.MessageResponse;

import java.io.IOException;

public class MessageResponseSerializer extends JsonSerializer<MessageResponse> {

    @Override
    public void serialize(MessageResponse messageResponse, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("message", messageResponse.getMessage());
        if (messageResponse.getMetaData() != null) {
            jsonGenerator.writeObjectField("meta_data", messageResponse.getMetaData());
        }
        jsonGenerator.writeEndObject();
    }
}

