package ma.emsi.bookingflight.repositories;

        import ma.emsi.bookingflight.entities.Role;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
        Role findByName(String name);
}

