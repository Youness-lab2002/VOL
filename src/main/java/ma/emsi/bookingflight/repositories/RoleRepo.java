package ma.emsi.bookingflight.repositories;

import ma.emsi.bookingflight.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    
}
