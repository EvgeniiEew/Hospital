package by.home.hospital.service.impl;

import by.home.hospital.domain.Appointment;
import by.home.hospital.domain.AppointmentUsers;
import by.home.hospital.domain.User;
import by.home.hospital.domain.ExaminationDoctor;
import by.home.hospital.service.IAppointmentUsersService;
import by.home.hospital.service.repository.AppointmentUsersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Service
@RequiredArgsConstructor
public class AppointmentUsersService implements IAppointmentUsersService {
    private final AppointmentUsersJpaRepository appointmentUsersJpaRepository;
    private final EpicrisisService epicrisisService;
    private final AppointmentService appointmentService;
    private final DiagnosisPatientService diagnosisPatientService;
    private final UserService userService;

    @Override
    public void setAppointmentsParameters(List<ExaminationDoctor> examinationDoctor) {
        for (ExaminationDoctor examination : examinationDoctor) {
            setAppointmentParameters(examination);
        }
    }

    @Override
    public void setAppointmentParameters(ExaminationDoctor examinationDoctor) {
        Appointment appointment = appointmentService.createAppointmentFormExaminationDoctorDto(examinationDoctor);
        epicrisisService.saveEpicrisFromExaminationDoctorDto(examinationDoctor, appointment);
        User patient = userService.getUserById(examinationDoctor.getPatientId());
        User doctor = userService.getUserById(examinationDoctor.getIdDoctor());
        AppointmentUsers appointmentUsers = new AppointmentUsers();
        appointmentUsers.setAppointment(appointment);
        appointmentUsers.setDoctor(doctor);
        appointmentUsers.setPatient(patient);
        appointmentUsersJpaRepository.save(appointmentUsers);
        diagnosisPatientService.saveDiagnosisPatientFromExaminationDoctorDto(examinationDoctor);
    }

    @Override
    public List<AppointmentUsers> getAppointmentUsers() {
        return appointmentUsersJpaRepository.findAll();
    }

    @Override
    public AppointmentUsers getAppointmentUsersByAppointmentId(Integer IdAppointment) {
        return appointmentUsersJpaRepository.getAppointmentUsersByAppointmentId(IdAppointment);
    }

    public AppointmentUsers save(AppointmentUsers appointmentUsers) {
        return appointmentUsersJpaRepository.save(appointmentUsers);
    }


    @Override
    public void deleteAppointmentUsers(Integer id) {
        appointmentUsersJpaRepository.deleteById(id);
    }


}
