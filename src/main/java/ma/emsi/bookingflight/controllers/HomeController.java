package ma.emsi.bookingflight.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "home";
    }
    @RequestMapping("/aPropos")
    public String about(){
        return "about";
    }

}
