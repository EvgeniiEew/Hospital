package by.home.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final String INDEX = "index";

    @GetMapping("/home")
    public String homePage() {
        return this.INDEX;
    }
}
