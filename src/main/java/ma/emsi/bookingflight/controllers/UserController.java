package ma.emsi.bookingflight.controllers;



import ma.emsi.bookingflight.entities.Reservation;
import ma.emsi.bookingflight.entities.User;
import ma.emsi.bookingflight.entities.Vol;
import ma.emsi.bookingflight.repositories.ReservationRepo;
import ma.emsi.bookingflight.repositories.UserRepository;
import ma.emsi.bookingflight.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("/user/vols")
public class UserController {

    private final VolService volService;
    private final ReservationRepo reservationRepo;
    private final UserRepository userRepository;

    @Autowired
    public UserController(VolService volService, ReservationRepo reservationRepo, UserRepository userRepository) {
        this.volService = volService;
        this.reservationRepo = reservationRepo;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getAllVols(Model model) {
        List<Vol> vols = volService.getAllVols();
        model.addAttribute("vols", vols);
        return "user/vols/list";  // la vue list.html sous templates/user/vols/
    }

    @GetMapping("/{id}")
    public String getVolById(@PathVariable Long id, Model model) {
        Vol vol = volService.getVolById(id);
        model.addAttribute("vol", vol);
        return "user/vols/view";  // la vue view.html sous templates/user/vols/
    }
    @PostMapping("/{id}/reserve")
    public String reserveVol(@PathVariable Long id, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Vol vol = volService.getVolById(id);
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setVol(vol);
        reservationRepo.save(reservation);
        return "redirect:/user/vols/reservations";
    }

    @GetMapping("/reservations")
    public String getUserReservations(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Reservation> reservations = reservationRepo.findByUser(user);
        model.addAttribute("reservations", reservations);
        return "user/reservations/list";  // la vue list.html sous templates/user/reservations/
    }
}