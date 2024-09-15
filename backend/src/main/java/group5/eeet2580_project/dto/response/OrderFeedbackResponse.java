package group5.eeet2580_project.dto.response;

import group5.eeet2580_project.entity.OrderFeedback;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderFeedbackResponse implements Data {
    private Long id;
    private Long orderID;
    private String text;
    private Integer rating;
    private Integer attentiveRating;
    private Integer friendlyRating;
    private Integer professionalRating;

    public OrderFeedbackResponse(OrderFeedback orderFeedback) {
        this.id = orderFeedback.getId();
        this.orderID = orderFeedback.getOrder().getId();
        this.text = orderFeedback.getText();
        this.rating = orderFeedback.getRating();
        this.attentiveRating = orderFeedback.getAttentiveRating();
        this.friendlyRating = orderFeedback.getFriendlyRating();
        this.professionalRating = orderFeedback.getProfessionalRating();
    }
}