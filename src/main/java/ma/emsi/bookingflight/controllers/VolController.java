package ma.emsi.bookingflight.controllers;



import ma.emsi.bookingflight.entities.Vol;
import ma.emsi.bookingflight.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class VolController {
    private final VolService volService;
    @Autowired
    public VolController(VolService volService) {
        this.volService = volService;
    }
    @GetMapping("/allVol")
    public String showAllVol(Model model){
        List<Vol> vols=volService.showAllVol();
        model.addAttribute("allVol",vols);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("dateFormatter", formatter);
        return "vols";
    }

}
