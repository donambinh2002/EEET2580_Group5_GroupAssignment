package group5.eeet2580_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sprayers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Sprayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @Column()
    private String cropType;

    @Column()
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id", updatable = false)
    private SprayerStore sprayerStore;
}
