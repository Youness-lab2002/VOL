package ma.emsi.bookingflight.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateDepart;
    private LocalDateTime dateArrive;

    @ManyToOne
    @JoinColumn(name = "aeroport_depart_id")
    private Aeroport aeroportDepart;

    @ManyToOne
    @JoinColumn(name = "aeroport_arrive_id")
    private Aeroport aeroportArrive;
}
