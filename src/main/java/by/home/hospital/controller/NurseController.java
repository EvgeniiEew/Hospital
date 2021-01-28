package by.home.hospital.controller;

import by.home.hospital.dto.AppointmentFulfillmentDto;
import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.service.impl.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NurseController {

    private final String FULFILLMENT_OF_APPOINTMENT = "FulfillmentOfAppointmentsList";
    @Autowired
    private AppointmentService appointmentService;

    //NURSE
    @GetMapping("/nurse/appointment")
    public String getPatientForFulfillmentOfAppointments(Model model) {
        List<AppointmentFulfillmentDto> appointmentUsers = this.appointmentService.nurseFindAllByStatus(AppointmentStatus.PENDING);
        model.addAttribute("appointmentUsers", appointmentUsers);
        return this.FULFILLMENT_OF_APPOINTMENT;
    }
}
