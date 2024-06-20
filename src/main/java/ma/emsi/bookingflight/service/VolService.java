package ma.emsi.bookingflight.service;


import ma.emsi.bookingflight.entities.Vol;
import ma.emsi.bookingflight.repositories.VolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolService {
    private VolRepo volRepo;

    @Autowired
    public VolService(VolRepo volRepo) {
        this.volRepo = volRepo;
    }

    public List<Vol> showAllVol(){
        return volRepo.findAll();
    }
}
