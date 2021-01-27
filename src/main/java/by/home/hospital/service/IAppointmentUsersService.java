package by.home.hospital.service;

import by.home.hospital.domain.AppointmentUsers;
import by.home.hospital.domain.ExaminationDoctor;

import java.util.List;

public interface IAppointmentUsersService {

    List<AppointmentUsers> getAppointmentUsers();

    void deleteAppointmentUsers(Integer number);

    void setAppointmentsParameters(List<ExaminationDoctor> examinationDoctor);

    void setAppointmentParameters(ExaminationDoctor examinationDoctor);

    AppointmentUsers getAppointmentUsersByAppointmentId(Integer IdAppointment);
}
