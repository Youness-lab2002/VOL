package ma.emsi.bookingflight.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/logout")
    public String logout(){
        return "login?logout";
    }
    @RequestMapping("/aPropos")
    public String about(){
        return "about";
    }

}
