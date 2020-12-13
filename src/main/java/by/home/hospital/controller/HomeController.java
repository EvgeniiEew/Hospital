package by.home.hospital.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model) {
        Date date = new Date();
        model.addAttribute("date" ,date);
        return "indexList";
    }


}
