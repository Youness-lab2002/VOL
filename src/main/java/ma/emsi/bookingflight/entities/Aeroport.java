package ma.emsi.bookingflight.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class Aeroport {
    @Id
    private Long id;
    private String name;
    private String vile;
    private String pays;
}
