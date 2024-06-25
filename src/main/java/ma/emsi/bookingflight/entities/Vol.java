package ma.emsi.bookingflight.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Vol {
    @Id
    private Long id;
    private LocalDateTime dateDepart;
    private LocalDateTime  dateArrive;
    @ManyToOne
    @JoinColumn(name = "aeroport_depart_id")
    private Aeroport aeroportDepart;
    @ManyToOne
    @JoinColumn(name = "aeroport_arrive_id")
    private Aeroport aeroportArrive;

    @OneToMany(mappedBy = "vol", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;
}
