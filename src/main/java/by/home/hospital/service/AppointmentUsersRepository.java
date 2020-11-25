package by.home.hospital.service;

import by.home.hospital.domain.AppointmentUsers;

import java.util.List;

public interface AppointmentUsersRepository {

    List<AppointmentUsers> getAppointmentUsers();

    void addAppointmentUsers(AppointmentUsers appointmentUsers);

    void deleteAppointmentUsers(Integer number);
}
