package by.home.hospital.controller;

import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.dto.PatientWhisStatusDto;
import by.home.hospital.enums.Type;
import by.home.hospital.service.IAppointmentUsersService;
import by.home.hospital.service.impl.PatientDetailsService;
import by.home.hospital.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private IAppointmentUsersService service;
    @Autowired
    PatientDetailsService patientDetailsService;

    @PostMapping(path = "/patient/examination", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerPatient(@RequestBody ExaminationDoctorDto examinationDoctorDto) {
        this.service.addAppointmentUsers(examinationDoctorDto);
    }


    private final String  roomExamination = "roomForExaminationList";
    // создает страницу для комната осмотра создания назначений принимает id пациента с кабинета осмотра
    //список назаначений , Type назначений + строку назаначений
    //отправляет поля для ExaminationDoctorDto
    @PostMapping("/patient/roomExamination/{id}/")
    public String getRoomForExamination(@PathVariable("id") Integer id , Model model){
        List<PatientWhisStatusDto> credentials = this.patientDetailsService.getUserById(id);
        model.addAttribute("credentials" , credentials);
        return this.roomExamination;
    }
}
