package group5.eeet2580_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "spray_orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayOrder implements Serializable {
    public enum Status {
        PENDING, CANCELLED, CONFIRMED, ASSIGNED, IN_PROGRESS, COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    private LocalDateTime timestamp;
    private float totalCost;
    private Integer rating;
    private String feedbackText;
    private Integer feedbackRating;

    @Enumerated(EnumType.STRING)
    private Status status;

    public float getTotalCost() {
        float result = 0;

        return result;
    }

//    public double getFarmlandArea() {
//        return Math.round((this.farmLandArea / 1000.0) * 10.0) / 10.0;
//    }
}
