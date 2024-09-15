package group5.eeet2580_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private SprayOrder order;

    @OneToMany
    @JoinColumn(name = "session_id")
    private List<User> sprayers;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeSlot timeSlot;

    public static TimeSlot getTimeSlot(LocalDateTime desiredStartTime){
        int hour = desiredStartTime.getHour();

        return switch (hour) {
            case 4 -> TimeSlot.MORNING1;
            case 5 -> TimeSlot.MORNING2;
            case 6 -> TimeSlot.MORNING3;
            case 7 -> TimeSlot.MORNING4;
            case 9 -> TimeSlot.NOON1;
            case 10 -> TimeSlot.NOON2;
            case 11 -> TimeSlot.NOON3;
            case 13 -> TimeSlot.NOON4;
            case 14 -> TimeSlot.NOON5;
            case 15 -> TimeSlot.NOON6;
            case 16 -> TimeSlot.AFTERNOON1;
            case 17 -> TimeSlot.AFTERNOON2;
            default -> null;
        };
    }

    public void setDateAndTimeSlot(LocalDateTime desiredStartTime){
        this.date = desiredStartTime.toLocalDate();
        this.timeSlot = getTimeSlot(desiredStartTime);
    }
}
