package by.home.hospital.service;

import by.home.hospital.domain.AppointmentUsers;
import by.home.hospital.dto.ExaminationDoctorDto;

import java.util.List;

public interface AppointmentUsersRepository {

    List<AppointmentUsers> getAppointmentUsers();

    void addAppointmentUsers(ExaminationDoctorDto examinationDoctorDto);

    void deleteAppointmentUsers(Integer number);
}
