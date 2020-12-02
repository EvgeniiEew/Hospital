package by.home.hospital.controller;

import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.service.DoctorDitalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorDitalesController {

    @Autowired
    private DoctorDitalesRepository repo;

    @GetMapping(path = "/Doctors")
    public List<DoctorInfoDto> getDoctorDitales() {
        return repo.getDoctorInfoDto();
    }


}
