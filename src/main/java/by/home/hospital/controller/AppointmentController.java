package by.home.hospital.controller;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.dto.*;
import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.enums.Type;
import by.home.hospital.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static java.lang.Integer.valueOf;

@Controller
public class AppointmentController {

    private final String ROOM_EXAMINATION = "roomForExaminationList";
    private final String FULFILLMENT_OF_APPOINTMENT = "FulfillmentOfAppointmentsList";
    private final String STATUS_PENDING_APPOINTMENT = "PendingAppointmentsList";
    private final String PERFORMANCE_APPOINTMENT = "performanceAppointmentList";
    @Autowired
    private ConversionService convertionService;
    @Autowired
    private EpicrisisService epicrisisService;
    @Autowired
    private UserService userService;
    @Autowired
    private AppointmentUsersService service;
    @Autowired
    private PatientDetailsService patientDetailsService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DiagnosisService diagnosisService;

//    @PostMapping("/patient/examination")
//    public String examinationPatient(String diagnosisDto, String epicrisis, String nameApointment,
//                                  String name, String idPatient, Authentication authentication) {
//        ExaminationDoctorDto examinationDoctorDto = new ExaminationDoctorDto(
//                valueOf(idPatient),
//                this.userService.getUserIdByCredentials_login(authentication.getName()),
//                diagnosisDto,
//                new AppointmentDto(nameApointment, Type.valueOf(name)),
//                epicrisis);
//        this.service.setAppointmentParameters(examinationDoctorDto);
//        return "redirect:/patient/status/receptionPending";
//    }
    @PostMapping("/patient/examination")
    public String examinationPatient(UserExaminationDto userExaminationDto , Authentication authentication) {
        userExaminationDto.setAuthentication(authentication);
        this.service.setAppointmentParameters(convertionService.convert(userExaminationDto,ExaminationDoctorDto.class));
        return "redirect:/patient/status/receptionPending";
    }
    // создает страницу для комната осмотра создания назначений принимает id пациента с кабинета осмотра
    //список назаначений , Type назначений + строку назаначений
    //отправляет поля для ExaminationDoctorDto
    @PostMapping("/patient/roomExamination/{id}/")
    public String getRoomForExamination(@PathVariable("id") Integer id, Model model) {
        PatientWhisStatusDto user = this.patientDetailsService.getUserById(id);
        model.addAttribute("user", user);
        return this.ROOM_EXAMINATION;
    }

    //Выполнение Назначений
    @GetMapping("/patient/roomExamination/FulfillmentOfAppointments")
    public String getPatientForFulfillmentOfAppointments(Model model) {
        List<AppointmentFulfillmentDto> appointmentUsers = this.appointmentService.findAllByStatus(AppointmentStatus.PENDING);
        // List<Diagnosis> diagnoses = this.diagnosisService.findAll();
        model.addAttribute("appointmentUsers", appointmentUsers);
        // model.addAttribute("diagnoses", diagnoses);
        return this.FULFILLMENT_OF_APPOINTMENT;
    }

    //выполненые назначения
    @GetMapping("/patient/roomExamination/StatusPendingApoitment")
    public String getPatientWhereStatusApointmentPending(Model model) {
        List<AppointmentFulfillmentDto> appointmentUsers = this.appointmentService.findAllByStatus(AppointmentStatus.DONE);
        List<Diagnosis> diagnoses = this.diagnosisService.findAll();
        model.addAttribute("appointmentUsers", appointmentUsers);
        model.addAttribute("diagnoses", diagnoses);
        return this.STATUS_PENDING_APPOINTMENT;
    }
//?
    // выполнение процедур.операций.медикаментов
    @PostMapping("/patient/roomExamination/FulfillmentOfAppointments/performance/{idAppointment}/")
    public String getPatientForPerfomanceAppointment(@PathVariable("idAppointment") Integer idAppointment, Model model) {
        MakingAppointmentsDto makingAppointmentsDto = this.appointmentService.getFormForMakingAppointmentsDto(idAppointment);
        model.addAttribute("makingAppointmentsDto", makingAppointmentsDto);
        return this.PERFORMANCE_APPOINTMENT;
    }

    //занесение результатов конечного выполнения процедур в базу //
    @PostMapping("/patient/roomExamination/FulfillmentOfAppointments/performance/result")
    public String getResultProcedures(ResultProcedurFormDto resultProcedurFormDto) {
        this.appointmentService.setPendingAppointmentStatusByID(resultProcedurFormDto);
        this.patientDetailsService.setStatusCheckoutPatientById(resultProcedurFormDto);
        this.epicrisisService.saveEpicris(resultProcedurFormDto);
        return "redirect:/patient/roomExamination/FulfillmentOfAppointments";
    }
}
