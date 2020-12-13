package by.home.hospital.controller;

import by.home.hospital.domain.Credentials;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.service.ICredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CredentialsController {

    @Autowired
    private ICredentialsService credentialsService;


    //auth controller
    @PostMapping("/patient/registers")
    public String registerPatient(Model model, @RequestParam String firstName, String lastName ,String  login, String password) {
        PatientRegisterDto patientRegisterDto = new PatientRegisterDto(firstName, lastName, login, password);

        credentialsService.registerPatient(patientRegisterDto);
        return "indexList" ;
    }
//    private final String patientRegister "regPatient";
//
//    @PostMapping(path = "/patient/register")
//    public void registerPatient(@RequestBody PatientRegisterDto patientRegisterDto) {
//        credentialsService.registerPatient(patientRegisterDto);
//        return;
//    }


        @GetMapping("/patient/register")
        public String registerPage(Model model) {
            Date date = new Date();
            model.addAttribute("date" ,date);
            return "patientRegisterList";
        }

        @GetMapping("/credanchials")
    public String  getCredentials(Model model) {
        List <Credentials> credentials = credentialsService.getCredentials().stream().collect(Collectors.toList());
        model.addAttribute("credentials",credentials);
        return "credentialsList";
    }
}
