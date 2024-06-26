package ma.emsi.bookingflight.controllers;


import ma.emsi.bookingflight.entities.*;
import ma.emsi.bookingflight.repositories.AeroportRepo;
import ma.emsi.bookingflight.repositories.ReservationRepo;
import ma.emsi.bookingflight.repositories.RoleRepository;
import ma.emsi.bookingflight.repositories.UserRepository;
import ma.emsi.bookingflight.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final VolService volService;
    private final ReservationRepo reservationRepo;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AeroportRepo aeroportRepo;

    @Autowired
    public AdminController(VolService volService, ReservationRepo reservationRepo, UserRepository userRepository,RoleRepository roleRepository,AeroportRepo aeroportRepo) {
        this.volService = volService;
        this.reservationRepo = reservationRepo;
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
        this.aeroportRepo=aeroportRepo;
    }

    @GetMapping("/vols")
    public String getAllVols(Model model) {
        List<Vol> vols = volService.getAllVols();
        model.addAttribute("vols", vols);
        return "admin/vols/list";  // la vue list.html sous templates/admin/vols/
    }

    @GetMapping("/vols/{id}")
    public String getVolById(@PathVariable Long id, Model model) {
        Vol vol = volService.getVolById(id);
        model.addAttribute("vol", vol);
        return "admin/vols/view";  // la vue view.html sous templates/admin/vols/
    }

    @GetMapping("/vols/new")
    public String showCreateForm(Model model) {
        model.addAttribute("vol", new Vol());
        return "admin/vols/form";  // la vue form.html sous templates/admin/vols/
    }

    @PostMapping("/vols")
    public String createVol(@ModelAttribute Vol vol) {
        volService.saveVol(vol);
        return "redirect:/admin/vols";
    }
    @GetMapping("/vols/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Vol vol = volService.getVolById(id);
        model.addAttribute("vol", vol);
        return "admin/vols/form";  // la vue form.html sous templates/admin/vols/
    }
    @PostMapping("/vols/{id}")
    public String updateVol(@PathVariable Long id, @ModelAttribute Vol vol) {
        volService.updateVol(id, vol);
        return "redirect:/admin/vols";
    }
    @GetMapping("/vols/{id}/delete")
    public String deleteVol(@PathVariable Long id) {
        volService.deleteVol(id);
        return "redirect:/admin/vols";
    }
    @GetMapping("/reservations")
    public String getAllReservations(Model model) {
        List<Reservation> reservations = reservationRepo.findAll();
        model.addAttribute("reservations", reservations);
        return "admin/reservations/list";  // la vue list.html sous templates/admin/reservations/
    }
    @GetMapping("/users")
    public String users(Model model){
        Set<Role> roles=new HashSet<>();
        roles.add(roleRepository.findByName("USER"));
        model.addAttribute("users",userRepository.findByRoles(roles));
        return "admin/vols/users";
    }
    @GetMapping("/aeroports")
    public String aeroports(Model model){
        model.addAttribute("aeroports",aeroportRepo.findAll());
        return "admin/vols/aeroport";
    }
}