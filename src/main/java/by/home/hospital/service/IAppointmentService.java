package by.home.hospital.service;

import by.home.hospital.domain.Appointment;
import by.home.hospital.dto.AppointmentFulfillmentDto;

import java.util.List;

public interface IAppointmentService {
    List<AppointmentFulfillmentDto> findAll();
//
//    void addAppointment(Appointment appointment);
//
//    void deleteAppointment(Integer number);
}
