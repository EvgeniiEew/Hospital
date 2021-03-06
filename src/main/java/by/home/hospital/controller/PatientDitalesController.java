package by.home.hospital.controller;

import by.home.hospital.dto.PatientWhisStatusDto;
import by.home.hospital.service.IPatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static by.home.hospital.domain.PatientStatus.NOT_EXAMINED;
import static by.home.hospital.domain.PatientStatus.RECEPTION_PENDING;

@Controller
public class PatientDitalesController {
    private final String PATIENT_NOT_EXAMID = "patientsNotExamindedList";
    private final String PATIENT_RECEPTION_PENDING = "patientReceptionPendingList";
    @Autowired
    private IPatientDetailsService iPatientDetailsService;

    @PostMapping("/patient/{id}/status")
    public String resetPatientStatusNotExaminedToReceptionPending(@PathVariable("id") Integer id) {
        iPatientDetailsService.patientStatusChangeToReceptionPending(id);
        return "redirect:/patient/status";
    }

    @GetMapping("/patient/status")
    public String getPatientWithStatusNotExamined(Model model) {
        List<PatientWhisStatusDto> patientsNotExaminded = iPatientDetailsService.getPatientWithStatus(NOT_EXAMINED);
        model.addAttribute("patientsNotExaminded", patientsNotExaminded);
        return PATIENT_NOT_EXAMID;
    }

    @GetMapping("/patient/status/receptionPending")
    public String getPatientWithStatusReceptionPending(Model model) {
        List<PatientWhisStatusDto> patientReceptionPending = iPatientDetailsService.getPatientWithStatus(RECEPTION_PENDING);
        model.addAttribute("patientReceptionPending", patientReceptionPending);
        return PATIENT_RECEPTION_PENDING;
    }

    @PostMapping("/patient/status/{id}/reset")
    public String resetPatientStatusReceptionPendingToNotExamined(@PathVariable("id") Integer id) {
        iPatientDetailsService.PatientStatusReceptionPendingToNotExaminet(id);
        return "redirect:/patient/status/receptionPending";
    }
}
