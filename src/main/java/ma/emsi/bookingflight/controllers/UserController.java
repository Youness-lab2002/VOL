package ma.emsi.bookingflight.controllers;



import ma.emsi.bookingflight.entities.Reservation;
import ma.emsi.bookingflight.entities.Role;
import ma.emsi.bookingflight.entities.User;
import ma.emsi.bookingflight.entities.Vol;
import ma.emsi.bookingflight.repositories.ReservationRepo;
import ma.emsi.bookingflight.repositories.RoleRepository;
import ma.emsi.bookingflight.repositories.UserRepository;
import ma.emsi.bookingflight.service.UserService;
import ma.emsi.bookingflight.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
@RequestMapping("/user/vols")
public class UserController {

    private final VolService volService;
    private final ReservationRepo reservationRepo;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public UserController(VolService volService, ReservationRepo reservationRepo, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.volService = volService;
        this.reservationRepo = reservationRepo;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    @GetMapping
    public String getAllVols(Model model,Principal principale) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!= null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails user = (UserDetails) principal;
                String username = user.getUsername();
                System.out.println("le nom de user connecter est : "+username);
            }
        }

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
        User user = userRepository.findByNom(principal.getName());
        Vol vol = volService.getVolById(id);
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setVol(vol);
        reservationRepo.save(reservation);
        return "redirect:/user/vols/reservations";
    }
    @GetMapping("/reservations")
    public String getUserReservations(Model model, Principal principal) {
        List<Reservation> res =new ArrayList<>();
        User user = userRepository.findByNom(principal.getName());
        for(Reservation i : user.getReservations()){
            res.add(i);
        }
        model.addAttribute("reservations", res);
        return "user/vols/reservations";  // la vue list.html sous templates/user/reservations/
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User u){
        Role role=roleRepository.findByName("user");
        System.out.println("le role est : "+role);
        u.setRoles(new ArrayList<>());
        u.getRoles().add(role);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userService.usersave(u);
        return "redirect:/login";
    }
    @GetMapping("/Signup")
    public String signup(){
        return "Signup";
    }
}