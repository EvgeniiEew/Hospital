package by.home.hospital.service;

import by.home.hospital.domain.AppointmentUsers;
import by.home.hospital.dto.ExaminationDoctorDto;

import java.util.List;

public interface IAppointmentUsersService {

    List<AppointmentUsers> getAppointmentUsers();

    void deleteAppointmentUsers(Integer number);

    void setAppointmentsParameters(List<ExaminationDoctorDto> examinationDoctorDto);

    void setAppointmentParameters(ExaminationDoctorDto examinationDoctorDto);

    AppointmentUsers getAppointmentUsersByAppointmentId(Integer IdAppointment);
}
