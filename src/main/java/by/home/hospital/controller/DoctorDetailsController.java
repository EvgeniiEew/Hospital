package by.home.hospital.controller;

import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.service.IDoctorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DoctorDetailsController {

    @Autowired
    private IDoctorDetailsRepository iDoctorDetailsRepository;

    private final String doctorInfoHtml = "doctorInfoDtosList";

    @GetMapping("/doctors")
    public String getDoctorDetails(Model model) {
        List<DoctorInfoDto> doctorInfoDtos = this.iDoctorDetailsRepository.getDoctorInfoDto();
        model.addAttribute("doctorInfoDtos", doctorInfoDtos);
        return this.doctorInfoHtml;
    }

    @PostMapping(path = "/doctor/register")
    public void registerDoctor(@ModelAttribute DoctorRegisterDto doctorRegisterDto, Model model) {
        this.iDoctorDetailsRepository.registerDoctor(doctorRegisterDto);
    }

}