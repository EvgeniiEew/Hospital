package by.home.hospital.controller;

import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.dto.NurseRegisterDto;
import by.home.hospital.service.impl.DoctorDetailsService;
import by.home.hospital.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DoctorDetailsController {
    private final String DOCTOR_INFO_DTO = "doctorInfoDtosList";
    private final String DOCTOR_CREATE = "doctorCreateList";

    @Autowired
    private UserService userService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private DoctorDetailsService doctorDetailsService;

    @GetMapping("/doctors")
    public String getDoctorDetails(Model model) {
        List<DoctorInfoDto> doctorInfoDtos = this.doctorDetailsService.getDoctorInfoDto();
        model.addAttribute("doctorInfoDtos", doctorInfoDtos);
        return this.DOCTOR_INFO_DTO;
    }

    //doctor/listRegister/
    @GetMapping("/doctor/create")
    public String setDoctor() {
        return this.DOCTOR_CREATE;
    }
//!!!
    @PostMapping(path = "/doctor/register")
    public String registerDoctor(DoctorRegisterDto doctorRegisterDto) {
        if (doctorRegisterDto.getDoctorDitales().equals("")) {
            //this.userService.saveNurse(Objects.requireNonNull(conversionService.convert(doctorRegisterDto, NurseRegisterDto.class)));
            this.userService.saveNurse(conversionService.convert(doctorRegisterDto, NurseRegisterDto.class));
            return "redirect:/";
        }
        this.doctorDetailsService.registerDoctor(doctorRegisterDto);
        return "redirect:/";
    }

}
