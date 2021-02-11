package by.home.hospital.controller;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.domain.ExaminationDoctor;
import by.home.hospital.dto.*;
import by.home.hospital.domain.AppointmentStatus;
import by.home.hospital.domain.Position;
import by.home.hospital.service.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class AppointmentController {

    private final String ROOM_EXAMINATION = "roomForExaminationList";
    private final String FULFILLMENT_OF_APPOINTMENT = "FulfillmentOfAppointmentsList";
    private final String STATUS_PENDING_APPOINTMENT = "PendingAppointmentsList";
    private final String PERFORMANCE_APPOINTMENT = "performanceAppointmentList";

    private final CredentialAuthService credentialAuthService;
    private final ConversionService conversionService;
    private final EpicrisisService epicrisisService;
    private final AppointmentUsersService appointmentUsersService;
    private final PatientDetailsService patientDetailsService;
    private final AppointmentService appointmentService;
    private final UserService userService;
    private final DiagnosisService diagnosisService;

    @PostMapping("/patient/examination/{id}/")
    public String examinationPatient(@PathVariable("id") Integer id,
                                     @Valid UserExaminationDto userExaminationDto,
                                     BindingResult bindingResult,
                                     Model model) {
        if (bindingResult.hasErrors()) {
            getModelExamination(id, model);
            return this.ROOM_EXAMINATION;
        }
        userExaminationDto.setAuthenticationDoctorId(this.credentialAuthService.getIdAutUser());
        this.appointmentUsersService.setAppointmentParameters(conversionService.convert(userExaminationDto, ExaminationDoctor.class));
        return "redirect:/patient/status/receptionPending";
    }

    @PostMapping("/patient/{id}/inspection")
    public String getRoomForExamination(@PathVariable("id") Integer id,
                                        Model model) {
        getModelExamination(id, model);
        return this.ROOM_EXAMINATION;
    }

    @GetMapping("/patient/appointment")
    public String getPatientForFulfillmentOfAppointments(Model model) {
        List<AppointmentFulfillmentDto> appointmentUsers = this.appointmentService.findAllByStatus(AppointmentStatus.PENDING);
        model.addAttribute("appointmentUsers", appointmentUsers);
        return this.FULFILLMENT_OF_APPOINTMENT;
    }

    @GetMapping("/patient/getAppointment")
    public String getPatientWhereStatusApointmentPending(Model model) {
        List<AppointmentFulfillmentDto> appointmentUsers = this.appointmentService.findAllByStatus(AppointmentStatus.DONE);
        List<Diagnosis> diagnoses = this.diagnosisService.findAll();
        model.addAttribute("appointmentUsers", appointmentUsers);
        model.addAttribute("diagnoses", diagnoses);
        return this.STATUS_PENDING_APPOINTMENT;
    }

    @PostMapping("/patient/FulfillmentOfAppointments/{idAppointment}/")
    public String getPatientForPerfomanceAppointment(@PathVariable("idAppointment") Integer idAppointment, Model model) {
        getModelAppointment(idAppointment, model);
        return this.PERFORMANCE_APPOINTMENT;
    }

    @PostMapping("/patient/addAppointmentToTheDatabase/{idAppointment}")
    public String getResultProcedures(@PathVariable("idAppointment") Integer idAppointment,
                                      @Valid ResultProcedurFormDto resultProcedurFormDto,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            getModelAppointment(idAppointment, model);
            return this.PERFORMANCE_APPOINTMENT;
        }
        this.appointmentService.setPendingAppointmentStatusByID(resultProcedurFormDto);
        this.patientDetailsService.setStatusCheckoutPatientById(resultProcedurFormDto);
        this.epicrisisService.saveEpicrisFromResultProcedureDto(resultProcedurFormDto);
        if (this.userService.getPositionByIdUser(this.credentialAuthService.getIdAutUser()).equals(Position.NURSE)) {
            return "redirect:/";
        }
        return "redirect:/patient/appointment";
    }

    private Model getModelAppointment(Integer idAppointment, Model model) {
        MakingAppointmentsDto makingAppointmentsDto = this.appointmentService.getFormForMakingAppointmentsDto(idAppointment);
        List<Diagnosis> diagnosis = makingAppointmentsDto.getDiagnoses();
        model.addAttribute("makingAppointmentsDto", makingAppointmentsDto);
        model.addAttribute("diagnosis", diagnosis);
        return model;
    }

    private Model getModelExamination(Integer id, Model model) {
        PatientWhisStatusDto user = this.patientDetailsService.getUserByIdPatientDetails(id);
        model.addAttribute("idPatient", id);
        model.addAttribute("user", user);
        return model;
    }
}
