package by.home.hospital.service;

import by.home.hospital.domain.AppointmentUsers;

import java.util.List;

public interface IAppointmentUsersService {

    List<AppointmentUsers> getAppointmentUsers();

  //  void addAppointmentUsers(ExaminationDoctorDto examinationDoctorDto);

    void deleteAppointmentUsers(Integer number);
}
