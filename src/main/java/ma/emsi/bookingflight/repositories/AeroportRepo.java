package ma.emsi.bookingflight.repositories;

import ma.emsi.bookingflight.entities.Aeroport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportRepo extends JpaRepository<Aeroport,Long> {

}
