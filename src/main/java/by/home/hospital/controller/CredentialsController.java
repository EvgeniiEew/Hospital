package by.home.hospital.controller;

import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.dto.UserEditDto;
import by.home.hospital.service.impl.CredentialsService;
import by.home.hospital.service.impl.PatientDetailsService;
import by.home.hospital.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CredentialsController {
    private final String CREDENTIALS = "credentialsList";
    private final String PATIENT_REGISTER = "patientRegisterList";
    private final CredentialsService credentialsService;
    private final UserService userService;
    private final PatientDetailsService patientDetailsService;

    @PostMapping("/patient/registers")
    public String registerPatient(@Valid PatientRegisterDto patientRegisterDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || this.credentialsService.existsByEmail(patientRegisterDto.getEmail())) {
            model.addAttribute("patientRegisterDto", patientRegisterDto);
            return this.PATIENT_REGISTER;
        }
        this.patientDetailsService.savePatientRegister(patientRegisterDto);
        return "redirect:/login";
    }

    @GetMapping("/patient/register")
    public String registerPage(PatientRegisterDto patientRegisterDto, Model model) {
        if (patientRegisterDto == null) {
            patientRegisterDto = new PatientRegisterDto();
        }
        model.addAttribute("patientRegisterDto", patientRegisterDto);
        return this.PATIENT_REGISTER;
    }

    @GetMapping("/credanchials")
    public String getCredentials(Model model) {
        List<UserEditDto> userEditList = this.userService.getUsersEditDto();
        model.addAttribute("userEditList", userEditList);
        return this.CREDENTIALS;
    }
}
