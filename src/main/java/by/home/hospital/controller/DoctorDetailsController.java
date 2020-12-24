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
    private final String DOCTOR_INFO_DTO = "doctorInfoDtosList";
    private final String DOCTOR_CREATE = "doctorCreateList";

    @Autowired
    private IDoctorDetailsRepository iDoctorDetailsRepository;

    @GetMapping("/doctors")
    public String getDoctorDetails(Model model) {
        List<DoctorInfoDto> doctorInfoDtos = this.iDoctorDetailsRepository.getDoctorInfoDto();
        model.addAttribute("doctorInfoDtos", doctorInfoDtos);
        return this.DOCTOR_INFO_DTO;
    }

    @GetMapping("/doctor/ceate")
    public String setDoctor(){
        return this.DOCTOR_CREATE;
    }

    @PostMapping(path = "/doctor/register")
    public String registerDoctor( DoctorRegisterDto doctorRegisterDto) {
        this.iDoctorDetailsRepository.registerDoctor(doctorRegisterDto);
        return "redirect:/home";
    }

}
