package ma.emsi.bookingflight.repositories;

import ma.emsi.bookingflight.entities.Aeroport;
import ma.emsi.bookingflight.entities.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface VolRepo extends JpaRepository<Vol,Long>{
    List<Vol> findByDateDepartAndAeroportArriveAndAeroportDepart(LocalDateTime dateDepart, Aeroport aeroportDepart, Aeroport aeroportArrive);
}
