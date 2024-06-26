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
import java.util.Optional;


@Controller

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
    @GetMapping("/")

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
        model.addAttribute("principal",principale);
        return "user/vols/list";  // la vue list.html sous templates/user/vols/
    }
    @GetMapping("/user/vols/{id}")
    public String getVolById(@PathVariable Long id, Model model,Principal principale) {
        Vol vol = volService.getVolById(id);
        model.addAttribute("vol", vol);
        model.addAttribute("principal",principale);
        return "user/vols/view";  // la vue view.html sous templates/user/vols/
    }
    @PostMapping("/user/vols/{id}/reserve")
    public String reserveVol(@PathVariable Long id,Model model, Principal principal) {
        Optional<User> user = userRepository.findByEmail(principal.getName());
        Vol vol = volService.getVolById(id);
        Reservation reservation = new Reservation();
        reservation.setUser(user.get());
        reservation.setVol(vol);
        reservationRepo.save(reservation);
        model.addAttribute("principal",principal);
        return "redirect:/user/vols/reservations";
    }
    @GetMapping("/user/vols/reservations")
    public String getUserReservations(Model model, Principal principal) {
        List<Reservation> res =new ArrayList<>();
        Optional<User> user = userRepository.findByEmail(principal.getName());
        for(Reservation i : user.get().getReservations()){
            res.add(i);
        }
        model.addAttribute("reservations", res);
        model.addAttribute("principal",principal);
        return "user/vols/reservations";  // la vue list.html sous templates/user/reservations/
    }
    @PostMapping("/user/vols/saveUser")
    public String saveUser(@ModelAttribute User u,Model model,Principal principal){
        Role role=roleRepository.findByName("user");
        System.out.println("le role est : "+role);
        u.setRoles(new ArrayList<>());
        u.getRoles().add(role);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userService.usersave(u);
        model.addAttribute("principal",principal);
        return "redirect:/login";
    }
    @GetMapping("/user/vols/Signup")
    public String signup(){
        return "Signup";
    }
}