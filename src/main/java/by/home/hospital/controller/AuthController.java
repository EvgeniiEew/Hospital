//package by.home.hospital.controller;
//
//import by.home.hospital.dto.PersonFormDto;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.validation.Valid;
//
//@Controller
//public class AuthController {
//
//    @GetMapping("/login")
//    public String showForm(PersonFormDto personFormDto) {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String validateLoginInfo(@Valid PersonFormDto personFormDto, Model model, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "login";
//        }
//        model.addAttribute("user", personFormDto.getLogin());
//        return "redirect:/";
//    }
//}
