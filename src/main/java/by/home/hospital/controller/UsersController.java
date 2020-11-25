package by.home.hospital.controller;


import by.home.hospital.domain.User;
import by.home.hospital.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UsersController {

    @Autowired
    private UserRepository repo;

    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveUser(@RequestBody User user) {
        repo.addUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return repo.getUsers();
    }
}
