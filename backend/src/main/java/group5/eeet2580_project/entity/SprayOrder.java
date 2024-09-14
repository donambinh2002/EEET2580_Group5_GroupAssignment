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

    public enum CropType{
        FRUIT, CEREAL, VEGETABLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float farmLandArea;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CropType cropType;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @OneToOne
    @JoinColumn(name = "session_id")
    private SpraySession spraySession;

    private LocalDateTime orderTime;
    private LocalDateTime desiredStartTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private String feedbackText;
    private Integer feedbackRating;

    public double getTotalCost() {
        return this.getFarmLandArea() * 30000;
    }

    public double getFarmlandAreaInMetres() {
        return Math.round((this.farmLandArea / 1000.0) * 10.0) / 10.0;
    }

    public void setFeedback(String feedbackText, Integer feedbackRating) {
        this.feedbackText = feedbackText;
        this.feedbackRating = feedbackRating;
    }
}
