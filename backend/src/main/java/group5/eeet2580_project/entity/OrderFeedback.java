package group5.eeet2580_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "order_feedbacks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderFeedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private SprayOrder order;

    // Overall rating
    private String text;
    private Integer rating;

    // Rating for the sprayer
    private Integer attentiveRating;
    private Integer friendlyRating;
    private Integer professionalRating;
}
