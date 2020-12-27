package by.home.hospital.service.impl;

import by.home.hospital.domain.*;
import by.home.hospital.dto.AppointmentDto;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.service.IAppointmentUsersService;
import by.home.hospital.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.home.hospital.enums.PatientStatus.CHECKING;

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
    private PatientDetailsService patientDetailsService;
    @Autowired
    private DiagnosisService diagnosisService;
    @Autowired
    private UserService userService;

    public Diagnosis createDiagnosis(String nameDiagnosis) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName(nameDiagnosis);
        return this.diagnosisService.save(diagnosis);
    }

    public PatientDetails createPatientDetails(Integer id) {
        PatientDetails patientDetails = this.patientDetailsService.getPatientDetailsByPatientId(id);
        patientDetails.setPatientStatus(CHECKING);
        return this.patientDetailsService.save(patientDetails);
    }

    public void setAppointmentParameters(ExaminationDoctorDto examinationDoctorDto) {
        this.setEpicrisis(examinationDoctorDto);
        DiagnosisPatient diagnosisPatient = new DiagnosisPatient();
        diagnosisPatient.setPatientDetails(this.createPatientDetails(examinationDoctorDto.getPatientIdDto()));
        diagnosisPatient.setDiagnosis(this.createDiagnosis(examinationDoctorDto.getDiagnosisDto()));
        this.diagnosisPatientService.save(diagnosisPatient);
    }


    public void setEpicrisis(ExaminationDoctorDto examinationDoctorDto) {

        AppointmentDto appointmentDto = examinationDoctorDto.getAppointmentDto();
        Appointment appointment = new Appointment(appointmentDto.getName(), appointmentDto.getType(), AppointmentStatus.PENDING);
        this.appointmentService.save(appointment);

        Epicrisis epicrisis = new Epicrisis();
        epicrisis.setInfo(examinationDoctorDto.getEpicrisis());
        epicrisis.setAppointment(appointment);
        this.epicrisisService.save(epicrisis);

        User patient = this.userService.getUserById(examinationDoctorDto.getPatientIdDto());
        User doctor = this.userService.getUserById(examinationDoctorDto.getIdDoctor());
        AppointmentUsers appointmentUsers = new AppointmentUsers();
        appointmentUsers.setAppointment(appointment);
        appointmentUsers.setDoctor(doctor);
        appointmentUsers.setPatient(patient);
        this.appointmentUsersJpaRepository.save(appointmentUsers);

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
