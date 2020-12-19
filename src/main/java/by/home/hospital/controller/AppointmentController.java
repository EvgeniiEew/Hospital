package by.home.hospital.controller;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.dto.AppointmentDto;
import by.home.hospital.dto.AppointmentFulfillmentDto;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.dto.PatientWhisStatusDto;
import by.home.hospital.enums.Type;
import by.home.hospital.service.IAppointmentUsersService;
import by.home.hospital.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

@Controller
public class AppointmentController {

    @Autowired
    private UserService userService;

    @Autowired
    private IAppointmentUsersService service;
    @Autowired
    private PatientDetailsService patientDetailsService;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DiagnosisService diagnosisService;

    //слить 1 параметр , починить id patienta в roomForExaminationList
    @PostMapping("/patient/examination")
    public String registerPatient(@RequestBody String diagnosisDto1, String diagnosisDto, String epicrisis, String nameApointment,
                                  String name, String idPatient, Authentication authentication) {
        int idpatient = valueOf(idPatient);
        String usernameLogin = authentication.getName();
        Integer idDoctor = this.userService.getUserIdByCredentials_login(usernameLogin);
        ArrayList<AppointmentDto> appointmentDto = new ArrayList<AppointmentDto>();
        AppointmentDto ad = new AppointmentDto(nameApointment, Type.valueOf(name));
        appointmentDto.add(ad);
        ExaminationDoctorDto examinationDoctorDto = new ExaminationDoctorDto(idpatient, idDoctor, diagnosisDto, appointmentDto, epicrisis);
        this.service.addAppointmentUsers(examinationDoctorDto);
        return "redirect:/patient/status/receptionPending";
    }


    private final String roomExamination = "roomForExaminationList";
    private final String FulfillmentOfAppointment = "FulfillmentOfAppointmentsList";

    // создает страницу для комната осмотра создания назначений принимает id пациента с кабинета осмотра
    //список назаначений , Type назначений + строку назаначений
    //отправляет поля для ExaminationDoctorDto
    @PostMapping("/patient/roomExamination/{id}/")
    public String getRoomForExamination(@PathVariable("id") Integer id, Model model) {
        List<PatientWhisStatusDto> credentials = this.patientDetailsService.getUserById(id);
        model.addAttribute("credentials", credentials);
        return this.roomExamination;
    }
    //Выполнение Назначений
    @GetMapping("/patient/roomExamination/FulfillmentOfAppointments")
    public String getPatientForFulfillmentOfAppointments(Model model){
        List<AppointmentFulfillmentDto> appointmentUsers = this.appointmentService.findAll();
        List<Diagnosis> diagnoses = this.diagnosisService.findAll();
        model.addAttribute("appointmentUsers" ,appointmentUsers);
        model.addAttribute("diagnoses" ,diagnoses);
        return this.FulfillmentOfAppointment;
    }
}
