package by.home.hospital.controller;


import by.home.hospital.domain.User;
import by.home.hospital.dto.MyViewDto;
import by.home.hospital.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// todo CRUD methods
@Controller
public class UsersController {
    @Autowired
    private ConversionService conversionService;

    private final String INDEX = "index";
    private final String VIEW = "myViewList";

    @Autowired
    private IUserServices repo;

    //просто /
    @GetMapping("/")
    public String homePage() {
        return this.INDEX;
    }
//    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void saveUser(@RequestBody User user) {
//        repo.addUser(user);
//    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return repo.getUsers();
    }

    @GetMapping("/myaccount")
    public String getMyView(Authentication authentication, Model model) {

        if (authentication == null) {
            return "redirect:/login";
        }
        String login = authentication.getName();
        MyViewDto view = (conversionService.convert(login, MyViewDto.class));
        model.addAttribute("view", view);
        model.addAttribute("login", login);
        return this.VIEW;
    }
}
