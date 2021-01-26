package by.home.hospital.service;

import by.home.hospital.domain.Appointment;
import by.home.hospital.dto.AppointmentDischarsergesDto;
import by.home.hospital.dto.AppointmentFulfillmentDto;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.enums.AppointmentStatus;

import java.util.List;

public interface IAppointmentService {
    List<AppointmentFulfillmentDto> findAll();

    List<AppointmentFulfillmentDto> nurseFindAllByStatus(AppointmentStatus status);

    Appointment createAppointmentFormExaminationDoctorDto(ExaminationDoctorDto examinationDoctorDto);

    List<Appointment> findAppointmentsByPatientId(Integer id);

    List<AppointmentDischarsergesDto> getAppontmentDischarsergesDto(Integer idPatient);
}
