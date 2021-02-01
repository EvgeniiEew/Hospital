package by.home.hospital.service;

import by.home.hospital.domain.Appointment;
import by.home.hospital.dto.AppointmentDischarsergesDto;
import by.home.hospital.dto.AppointmentFulfillmentDto;
import by.home.hospital.domain.ExaminationDoctor;
import by.home.hospital.domain.AppointmentStatus;

import java.util.List;

public interface IAppointmentService {
    List<AppointmentFulfillmentDto> findAll();

    List<AppointmentFulfillmentDto> nurseFindAllByStatus(AppointmentStatus status);

    Appointment createAppointmentFormExaminationDoctorDto(ExaminationDoctor examinationDoctor);

    List<Appointment> findAppointmentsByPatientId(Integer id);

    List<AppointmentDischarsergesDto> getAppontmentDischarsergesDto(Integer idPatient);
}
