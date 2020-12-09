package by.home.hospital.controller;

import by.home.hospital.domain.Credentials;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.service.ICredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CredentialsController {
    @Autowired
    private ICredentialsService credentialsService;


    //auth controller
    @PostMapping(path = "/patient/register" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerPatient(@RequestBody PatientRegisterDto patientRegisterDto) {
        credentialsService.registerPatient(patientRegisterDto);
    }


    @GetMapping("/credanchials")
    public String  getCredentials(Model model) {
        List <Credentials> credentials = credentialsService.getCredentials().stream().collect(Collectors.toList());
        model.addAttribute("credentials",credentials);
        return "credentialsList";
    }
}
