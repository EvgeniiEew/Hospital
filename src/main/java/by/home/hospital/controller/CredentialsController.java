package by.home.hospital.controller;

import by.home.hospital.domain.Credentials;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.service.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CredentialsController {
    @Autowired
    private CredentialsRepository repo;


    //auth controller
    @PostMapping(path = "/register/patient" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerPatient(@RequestBody PatientRegisterDto patientRegisterDto) {
        repo.registerPatient(patientRegisterDto);
    }


    @GetMapping("/credanchials")
    public List<Credentials>  getCredentials() {
        return repo.getCredentials();
    }
}
