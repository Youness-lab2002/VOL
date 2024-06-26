package ma.emsi.bookingflight.repositories;

        import ma.emsi.bookingflight.entities.Role;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
        public List<Role> findByName(String name);
}

