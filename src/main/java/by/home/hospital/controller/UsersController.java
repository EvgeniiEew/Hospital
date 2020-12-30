package by.home.hospital.controller;


import by.home.hospital.domain.User;
import by.home.hospital.dto.Avatar;
import by.home.hospital.dto.MyViewDto;
import by.home.hospital.service.IUserServices;
import by.home.hospital.service.StorageService;
import by.home.hospital.service.impl.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// todo CRUD methods
@Controller
public class UsersController {


    private final String INDEX = "index";
    private final String VIEW = "myViewList";
    @Autowired
    private UserService userService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private StorageService imgService;
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

    @PostMapping("/users/{id}/img")
    public String handleFileUpload(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        imgService.store(id, file);
        return "redirect:/myaccount";
    }

    @GetMapping("/users/{id}/img")
    public void getImmage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Avatar file = this.imgService.getFile(id);
        if (file != null) {
            try (InputStream is = file.getData()) {
                IOUtils.copy(is, response.getOutputStream());
            }
        }
    }
}
