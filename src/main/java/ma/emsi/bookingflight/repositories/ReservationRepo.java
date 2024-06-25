package ma.emsi.bookingflight.repositories;

import ma.emsi.bookingflight.entities.Reservation;
import ma.emsi.bookingflight.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {
    List<Reservation> findByUser(User user);
}
