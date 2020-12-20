package by.home.hospital.controller;

import by.home.hospital.dto.PatientWhisStatusDto;
import by.home.hospital.service.IPatientDetailsService;
import by.home.hospital.service.impl.PatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static by.home.hospital.enums.PatientStatus.NOT_EXAMINED;
import static by.home.hospital.enums.PatientStatus.RECEPTION_PENDING;

@Controller
public class PatientDitalesController {
    @Autowired
    private IPatientDetailsService service;
    @Autowired
    private PatientDetailsService patientDetailsService;

    @PostMapping("/patient/{id}/status")
    public String resetPatientStatusNotExaminedToReceptionPending(@PathVariable("id") Integer id) {
        service.patientStatusСhangeToReceptionPending(id);
        return "redirect:/patient/status";
    }

    private final String patientNotExamid = "patientsNotExamindedList";
    private final String patientReceptionPending = "patientReceptionPendingList";

    //зарегистрировавшиеся не добавленые на прием  пациенты
    @GetMapping("/patient/status")
    public String getPatientWithStatusNotExamined(Model model) {
        List<PatientWhisStatusDto> patientsNotExaminded = this.patientDetailsService.getPatientWithStatus(NOT_EXAMINED);
        model.addAttribute("patientsNotExaminded", patientsNotExaminded);
        return this.patientNotExamid;
    }

    //записаны на прием в ожидании назначения
    @GetMapping("/patient/status/receptionPending")
    public String getPatientWithStatusReceptionPending(Model model) {
        List<PatientWhisStatusDto> patientReceptionPending = this.patientDetailsService.getPatientWithStatus(RECEPTION_PENDING);
        model.addAttribute("patientReceptionPending", patientReceptionPending);
        return this.patientReceptionPending;
    }

    @PostMapping("/patient/status/{id}/reset")
    public String resetPatientStatusReceptionPendingToNotExamined(@PathVariable("id") Integer id) {
        service.PatientStatusReceptionPendingToNotExaminet(id);
        return "redirect:/patient/status/receptionPending";
    }
}
