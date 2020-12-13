package by.home.hospital.controller;

import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.service.IAppointmentUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

    @Autowired
    private IAppointmentUsersService service;

    @PostMapping(path = "/patient/examination", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerPatient(@RequestBody ExaminationDoctorDto examinationDoctorDto) {
        service.addAppointmentUsers(examinationDoctorDto);
    }
}
