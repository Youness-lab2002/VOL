package ma.emsi.bookingflight.repositories;
import ma.emsi.bookingflight.entities.Reservation;
import ma.emsi.bookingflight.entities.Role;
import ma.emsi.bookingflight.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, String> {
    User findByNom(String username);
    List<User> findByRoles(Set<Role> role);
    Optional<User> findByEmail(String email);
}
