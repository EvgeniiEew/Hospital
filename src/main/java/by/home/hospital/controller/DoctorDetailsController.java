package by.home.hospital.controller;

import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.dto.NurseRegisterDto;
import by.home.hospital.domain.Position;
import by.home.hospital.service.impl.CredentialsService;
import by.home.hospital.service.impl.DoctorDetailsService;
import by.home.hospital.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DoctorDetailsController {
    private final String DOCTOR_INFO_DTO = "doctorInfoDtosList";
    private final String DOCTOR_CREATE = "doctorCreateList";

    private final CredentialsService credentialsService;
    private final UserService userService;
    private final ConversionService conversionService;
    private final DoctorDetailsService doctorDetailsService;

    @GetMapping("/doctors")
    public String getDoctorDetails(Model model) {
        List<DoctorInfoDto> doctorInfoDtos = doctorDetailsService.getDoctorInfoDto();
        model.addAttribute("doctorInfoDtos", doctorInfoDtos);
        return DOCTOR_INFO_DTO;
    }

    @GetMapping("/doctor/create")
    public String setDoctor(DoctorRegisterDto doctorRegisterDto, Model model) {
        if (doctorRegisterDto == null) {
            doctorRegisterDto = new DoctorRegisterDto();
        }
        model.addAttribute("doctorRegisterDto", doctorRegisterDto);
        return DOCTOR_CREATE;
    }

    @PostMapping(path = "/doctor/register")
    public String registerDoctor(@Valid DoctorRegisterDto doctorRegisterDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || credentialsService.existsByEmail(doctorRegisterDto.getEmail())) {
            model.addAttribute("doctorRegisterDto", doctorRegisterDto);
            return DOCTOR_CREATE;
        }
        if (doctorRegisterDto.getPosition().equals(Position.NURSE)) {
            userService.saveNurse(conversionService.convert(doctorRegisterDto, NurseRegisterDto.class));
            return "redirect:/";
        }
        doctorDetailsService.registerDoctor(doctorRegisterDto);
        return "redirect:/";
    }

}
