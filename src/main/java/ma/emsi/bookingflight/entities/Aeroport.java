package ma.emsi.bookingflight.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Aeroport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ville;  // Correction d'une faute de frappe : 'vile' en 'ville'
    private String pays;

    @OneToMany(mappedBy = "aeroportDepart")
    private List<Vol> volsDepart;  // Changement de nom pour plus de clarté

    @OneToMany(mappedBy = "aeroportArrive")
    private List<Vol> volsArrive;  // Correction d'une faute de frappe : 'volsArrive'
}
