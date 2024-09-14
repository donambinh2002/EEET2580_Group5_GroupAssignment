package group5.eeet2580_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "spray_sessions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SpraySession implements Serializable {
    public enum TimeSlot{
        MORNING1, MORNING2, MORNING3, MORNING4, NOON1, NOON2, NOON3, NOON4, NOON5, NOON6, AFTERNOON1, AFTERNOON2
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private SprayOrder order;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User sprayer;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeSlot timeSlot;
}
