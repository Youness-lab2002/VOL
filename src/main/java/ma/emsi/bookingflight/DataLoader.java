package ma.emsi.bookingflight;

import ma.emsi.bookingflight.entities.Aeroport;
import ma.emsi.bookingflight.entities.Reservation;
import ma.emsi.bookingflight.entities.Vol;
import ma.emsi.bookingflight.repositories.AeroportRepo;
import ma.emsi.bookingflight.repositories.VolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner{

    private final VolRepo volRepo;
    private final AeroportRepo aeroportRepo;
    @Autowired
    public DataLoader(VolRepo volRepo, AeroportRepo aeroport) {
        this.volRepo = volRepo;
        this.aeroportRepo = aeroport;
    }

    @Override
    public void run(String... args) throws Exception {
        Aeroport aeroport=new Aeroport(1L,"Mohemmed5","casablanca","MAROC");
        Aeroport aeroport1=new Aeroport(2L,"Aéroport international de Dubaï","Dubaï","Émirats arabes unis");
        Aeroport aeroport3=new Aeroport(2L,"Aéroport international du Caire"," Le Caire","Égypte");
        Aeroport aeroport4=new Aeroport(2L,"Aéroport international Roi Khaled","Riyad","Qatar");
        Aeroport aeroport5=new Aeroport(2L,"Aéroport international de Beyrouth-Rafic Hariri","Beyrouth","Liban");

        aeroportRepo.save(aeroport);
        aeroportRepo.save(aeroport1);
        aeroportRepo.save(aeroport3);
        aeroportRepo.save(aeroport4);
        aeroportRepo.save(aeroport5);

        for(int i=1;i<=10;i++){
            Vol vol=new Vol((long) i, LocalDateTime.now(),LocalDateTime.now(),aeroport,aeroport1,null);
            volRepo.save(vol);
        }
    }
}
