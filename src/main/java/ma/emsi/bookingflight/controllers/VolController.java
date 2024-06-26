package ma.emsi.bookingflight.controllers;



import ma.emsi.bookingflight.entities.Vol;
import ma.emsi.bookingflight.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("/vols")
public class VolController {
    @Autowired
    private VolService volService;

    @GetMapping
    public String getAllVols(Model model) {
        List<Vol> vols = volService.getAllVols();
        model.addAttribute("vols", vols);
        return "vols/list";  // la vue list.html sous templates/vols/
    }
    @GetMapping("/hi")
    public String hi(){
        return "amine.html";
    }
    @GetMapping("/{id}")
    public String getVolById(@PathVariable Long id, Model model) {
        Vol vol = volService.getVolById(id);
        model.addAttribute("vol", vol);
        return "vols/view";  // la vue view.html sous templates/vols/
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("vol", new Vol());
        return "vols/form";  // la vue form.html sous templates/vols/
    }

    @PostMapping
    public String createVol(@ModelAttribute Vol vol) {
        volService.saveVol(vol);
        return "redirect:/vols";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Vol vol = volService.getVolById(id);
        model.addAttribute("vol", vol);
        return "vols/form";  // la vue form.html sous templates/vols/
    }

    @PostMapping("/{id}")
    public String updateVol(@PathVariable Long id, @ModelAttribute Vol vol) {
        vol.setId(id);
        volService.saveVol(vol);
        return "redirect:/vols";
    }

    @GetMapping("/{id}/delete")
    public String deleteVol(@PathVariable Long id) {
        volService.deleteVol(id);
        return "redirect:/vols";
    }

    @GetMapping("/search")
    public String searchVols(@RequestParam LocalDateTime dateDepart, @RequestParam LocalDateTime dateArrive, Model model) {
        List<Vol> vols = volService.searchVols(dateDepart, dateArrive);
        model.addAttribute("vols", vols);
        return "vols/list";  // la vue list.html sous templates/vols/
    }
}