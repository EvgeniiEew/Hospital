package by.home.hospital.service.impl;

import by.home.hospital.domain.Appointment;
import by.home.hospital.domain.AppointmentUsers;
import by.home.hospital.domain.User;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.dto.UserExaminationDto;
import by.home.hospital.service.IAppointmentUsersService;
import by.home.hospital.service.repository.AppointmentUsersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    public void setAppointmentsParameters(List<ExaminationDoctorDto> examinationDoctorDto) {
        for (ExaminationDoctorDto examination : examinationDoctorDto) {
            this.setAppointmentParameters(examination);
        }
    }

    public void setAppointmentParameters(ExaminationDoctorDto examinationDoctorDto) {
        Appointment appointment = this.appointmentService.createAppointmentFormExaminationDoctorDto(examinationDoctorDto);
        this.epicrisisService.saveEpicrisFromExaminationDoctorDto(examinationDoctorDto, appointment);
        User patient = this.userService.getUserById(examinationDoctorDto.getPatientId());
        User doctor = this.userService.getUserById(examinationDoctorDto.getIdDoctor());
        AppointmentUsers appointmentUsers = new AppointmentUsers();
        appointmentUsers.setAppointment(appointment);
        appointmentUsers.setDoctor(doctor);
        appointmentUsers.setPatient(patient);
        this.appointmentUsersJpaRepository.save(appointmentUsers);
        this.diagnosisPatientService.saveDiagnosisPatientFromExaminationDoctorDto(examinationDoctorDto);
    }

    @Override
    public List<AppointmentUsers> getAppointmentUsers() {
        return this.appointmentUsersJpaRepository.findAll();
    }

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
