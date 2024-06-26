package ma.emsi.bookingflight.repositories;
import ma.emsi.bookingflight.entities.Role;
import ma.emsi.bookingflight.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    List<User> findByRoles(Set<Role> role);
}
