package group5.eeet2580_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "sprayer_stores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SprayerStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    @OneToOne(mappedBy = "sprayerStore", cascade = CascadeType.ALL)
    private User owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sprayerStore", cascade = CascadeType.ALL)
    private Collection<Sprayer> sprayers;
}