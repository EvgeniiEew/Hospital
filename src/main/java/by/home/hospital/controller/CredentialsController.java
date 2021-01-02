//package by.home.hospital.controller;
//
//import by.home.hospital.domain.Credentials;
//import by.home.hospital.dto.PatientRegisterDto;
//import by.home.hospital.service.ICredentialsService;
//import by.home.hospital.service.impl.PatientDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//public class CredentialsController {
//    private final String CREDENTIALS = "credentialsList";
//    private final String PATIENT_REGISTER = "patientRegisterList";
//
//    @Autowired
//    private ICredentialsService credentialsService;
//    @Autowired
//    private PatientDetailsService patientDetailsService;
//
//    @PostMapping("/patient/registers")
//    public String registerPatient(PatientRegisterDto patientRegisterDto) {
//        this.patientDetailsService.savePatientRegister(patientRegisterDto);
//        return "redirect:/";
//    }
//
//    @GetMapping("/patient/register")
//    public String registerPage() {
//        return this.PATIENT_REGISTER;
//    }
//
//    @GetMapping("/credanchials")
//    public String getCredentials(Model model) {
//        List<Credentials> credentials = credentialsService.getCredentials().stream().collect(Collectors.toList());
//        model.addAttribute("credentials", credentials);
//        return this.CREDENTIALS;
//    }
//}
