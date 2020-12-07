package by.home.hospital.controller;

import by.home.hospital.service.IPatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientDitalesController {
    @Autowired
    private IPatientDetailsService service;

    @PostMapping(path = "/patient/signup" )
    public void patientStatusСhange(@RequestParam("number") Integer number) {
        service.patientStatusСhange(number);
    }
}
