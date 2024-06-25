package ma.emsi.bookingflight.service;


import ma.emsi.bookingflight.entities.Aeroport;
import ma.emsi.bookingflight.entities.Vol;
import ma.emsi.bookingflight.repositories.VolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VolService {
    private VolRepo volRepo;

    @Autowired
    public VolService(VolRepo volRepo) {
        this.volRepo = volRepo;
    }


    public List<Vol> getAllVols() {
        return volRepo.findAll();
    }

    public Vol getVolById(Long id) {
        return volRepo.findById(id).orElseThrow(()->new RuntimeException("vol not exist"));
    }

    public void saveVol(Vol vol) {
        volRepo.save(vol);
    }
    public void updateVol(Long id, Vol vol) {
        if (volRepo.existsById(id)) {
            vol.setId(id);
            volRepo.save(vol);
        }
    }
    public void deleteVol(Long id) {
        volRepo.deleteById(id);
    }

    public List<Vol> searchVols(LocalDateTime dateDepart, Aeroport aeroportDepart,Aeroport aeroportArrive){
        return volRepo.findByDateDepartAndAeroportArriveAndAeroportDepart(dateDepart,aeroportDepart,aeroportArrive);
    }
}
