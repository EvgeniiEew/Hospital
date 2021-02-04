package by.home.hospital.service.impl;

import by.home.hospital.domain.Appointment;
import by.home.hospital.domain.AppointmentUsers;
import by.home.hospital.domain.User;
import by.home.hospital.domain.ExaminationDoctor;
import by.home.hospital.service.IAppointmentUsersService;
import by.home.hospital.service.repository.AppointmentUsersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class AppointmentUsersService implements IAppointmentUsersService {
    @Autowired
    private AppointmentUsersJpaRepository appointmentUsersJpaRepository;
    @Autowired
    private EpicrisisService epicrisisService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DiagnosisPatientService diagnosisPatientService;
    @Autowired
    private UserService userService;

    @Override
    public void setAppointmentsParameters(List<ExaminationDoctor> examinationDoctor) {
        for (ExaminationDoctor examination : examinationDoctor) {
            this.setAppointmentParameters(examination);
        }
    }

    @Override
    public void setAppointmentParameters(ExaminationDoctor examinationDoctor) {
        Appointment appointment = this.appointmentService.createAppointmentFormExaminationDoctorDto(examinationDoctor);
        this.epicrisisService.saveEpicrisFromExaminationDoctorDto(examinationDoctor, appointment);
        User patient = this.userService.getUserById(examinationDoctor.getPatientId());
        User doctor = this.userService.getUserById(examinationDoctor.getIdDoctor());
        AppointmentUsers appointmentUsers = new AppointmentUsers();
        appointmentUsers.setAppointment(appointment);
        appointmentUsers.setDoctor(doctor);
        appointmentUsers.setPatient(patient);
        this.appointmentUsersJpaRepository.save(appointmentUsers);
        this.diagnosisPatientService.saveDiagnosisPatientFromExaminationDoctorDto(examinationDoctor);
    }

    @Override
    public List<AppointmentUsers> getAppointmentUsers() {
        return this.appointmentUsersJpaRepository.findAll();
    }

    @Override
    public AppointmentUsers getAppointmentUsersByAppointmentId(Integer IdAppointment) {
        return this.appointmentUsersJpaRepository.getAppointmentUsersByAppointmentId(IdAppointment);
    }

    public AppointmentUsers save(AppointmentUsers appointmentUsers) {
        return this.appointmentUsersJpaRepository.save(appointmentUsers);
    }


    @Override
    public void deleteAppointmentUsers(Integer id) {
        this.appointmentUsersJpaRepository.deleteById(id);
    }


}
