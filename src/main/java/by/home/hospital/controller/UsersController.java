package by.home.hospital.controller;


import by.home.hospital.domain.User;
import by.home.hospital.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// todo CRUD methods
@Controller
public class UsersController {

    private final String INDEX = "index";

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


}
