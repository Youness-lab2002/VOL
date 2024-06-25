package ma.emsi.bookingflight.repositories;
import ma.emsi.bookingflight.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
