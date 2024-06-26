package ma.emsi.bookingflight.controllers;

import ma.emsi.bookingflight.entities.Role;
import ma.emsi.bookingflight.entities.User;
import ma.emsi.bookingflight.repositories.RoleRepository;
import ma.emsi.bookingflight.repositories.UserRepository;
import ma.emsi.bookingflight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/Signup")
    public String signup(){
        return "Signup";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User u){
        List<Role> role=roleRepository.findByName("user");
        System.out.println("le role est : "+role.getFirst());
        u.setRoles(new ArrayList<>());
        u.getRoles().add(role.getFirst());
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userService.usersave(u);
        return "redirect:/login";
    }
}
