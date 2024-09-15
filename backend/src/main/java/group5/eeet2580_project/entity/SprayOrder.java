package group5.eeet2580_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "spray_orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String cropType;
    private double farmLandArea;
    private LocalDate desiredDate;
    private LocalTime desiredTime;
    private double totalCost;
    private Integer rating;
    private String feedbackText;
    private Integer feedbackRating;
    private Integer attentiveRating;
    private Integer friendlyRating;
    private Integer professionalRating;

    public enum Status {
        PENDING, CANCELLED, CONFIRMED, ASSIGNED, IN_PROGRESS, COMPLETED
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    public double getFarmlandArea() {
        return Math.round((this.farmLandArea / 1000.0) * 10.0) / 10.0;
    }
}
