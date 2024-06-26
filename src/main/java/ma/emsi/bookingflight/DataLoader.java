package ma.emsi.bookingflight;

import jakarta.transaction.Transactional;
import ma.emsi.bookingflight.entities.Aeroport;
import ma.emsi.bookingflight.entities.Role;
import ma.emsi.bookingflight.entities.User;
import ma.emsi.bookingflight.entities.Vol;
import ma.emsi.bookingflight.repositories.AeroportRepo;
import ma.emsi.bookingflight.repositories.RoleRepository;
import ma.emsi.bookingflight.repositories.UserRepository;
import ma.emsi.bookingflight.repositories.VolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final VolRepo volRepo;
    private final AeroportRepo aeroportRepo;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(VolRepo volRepo, AeroportRepo aeroportRepo, UserRepository userRepository, RoleRepository roleRepository) {
        this.volRepo = volRepo;
        this.aeroportRepo = aeroportRepo;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Aeroport aeroport = Aeroport.builder().name("Mohammed V").ville("Casablanca").pays("MAROC").build();
        Aeroport aeroport1 = Aeroport.builder().name("Dubai").ville("Dubai").pays("UAE").build();
        aeroportRepo.save(aeroport);
        aeroportRepo.save(aeroport1);

        for (int i = 1; i <= 10; i++) {
            Vol vol = Vol.builder()
                    .dateDepart(LocalDateTime.now())
                    .dateArrive(LocalDateTime.now().plusHours(3)) // Exemple pour ajouter 3 heures
                    .aeroportDepart(aeroport)
                    .aeroportArrive(aeroport1)
                    .build();
            volRepo.save(vol);
        }

        Role userRole = new Role();
        userRole.setName("user");
        roleRepository.save(userRole);

        Role adminRole=new Role();
        adminRole.setName("admin");
        roleRepository.save(adminRole);
        Role userRoles = roleRepository.findByName("user");

        User user=new User();
        user.setNom("Brahim");
        user.setEmail("Brahim@gmail.com");
        user.setCIN("BJ667788");
        user.setAdresse("casa");
        user.setPassword("Brahim2001");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);
        User user1=new User();

        user1.setNom("Youness");
        user1.setEmail("Youness@gmail.com");
        user1.setCIN("BJ667700");
        user1.setAdresse("casa");
        user1.setPassword("Youness2002");
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setTele("0677885544");
        user1.setRoles(Arrays.asList(userRole));
        userRepository.save(user1);
    }
}
