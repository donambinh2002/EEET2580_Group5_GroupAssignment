package group5.eeet2580_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "spray_sessions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SpraySession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private SprayOrder order;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User sprayer;
    
    private LocalDate date;
    private String timeSlot;
    private Boolean isBooked;
}
