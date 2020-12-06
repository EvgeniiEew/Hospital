package by.home.hospital.controller;

import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.service.IDoctorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorDetailsController {

    @Autowired
    private IDoctorDetailsRepository repo;

    @GetMapping(path = "/doctor")
    public List<DoctorInfoDto> getDoctorDetails() {
        return repo.getDoctorInfoDto();
    }

    @PostMapping(path = "/doctor/register" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerDoctor(@RequestBody DoctorRegisterDto doctorRegisterDto) {
        repo.registerDoctor(doctorRegisterDto);
    }

}
