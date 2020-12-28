package by.home.hospital.controller;

import by.home.hospital.domain.Credentials;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.service.ICredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CredentialsController {
    private final String IDNEX = "index";
    private final String CREDENTIALS = "credentialsList";
    private final String PATIENT_REGISTER = "patientRegisterList";

    @Autowired
    private ICredentialsService credentialsService;


    //auth controller
    @PostMapping("/patient/registers")
    public String registerPatient(@RequestParam String firstName, String lastName, String login, String password) {
        PatientRegisterDto patientRegisterDto = new PatientRegisterDto(firstName, lastName, login, password);
        credentialsService.registerPatient(patientRegisterDto);
        return this.IDNEX;
    }

    @GetMapping("/patient/register")
    public String registerPage() {
        return this.PATIENT_REGISTER;
    }

    @GetMapping("/credanchials")
    public String getCredentials(Model model) {
        List<Credentials> credentials = credentialsService.getCredentials().stream().collect(Collectors.toList());
        model.addAttribute("credentials", credentials);
        return this.CREDENTIALS;
    }
}
