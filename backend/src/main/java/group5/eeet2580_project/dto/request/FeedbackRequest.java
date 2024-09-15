package group5.eeet2580_project.dto.request;

import lombok.Data;

@Data
public class FeedbackRequest {
    private String feedbackText;
    private Integer feedbackRating;
    private Integer attentiveRating;
    private Integer friendlyRating;
    private Integer professionalRating;
}
