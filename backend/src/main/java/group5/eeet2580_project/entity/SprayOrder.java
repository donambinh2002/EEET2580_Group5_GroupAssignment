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
    private String status;
    private Integer rating;

}
