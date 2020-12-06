package by.home.hospital.service.impl;

import by.home.hospital.domain.*;
import by.home.hospital.dto.AppointmentDto;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.service.IAppointmentUsersService;
import by.home.hospital.service.IPatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static by.home.hospital.enums.Status.CHECKING;

@Transactional
@Service
public class AppointmentUsersService implements IAppointmentUsersService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IPatientDetailsService IPatientDetailsService;

    //todo
    @Override
    public void addAppointmentUsers(ExaminationDoctorDto examinationDoctorDto) {
        for (AppointmentDto appointmentDto : examinationDoctorDto.getAppointmentArray()) {
            Appointment appointment = new Appointment(appointmentDto.getName(), appointmentDto.getType(), AppointmentStatus.DONE);
            Epicrisis epicrisis = new Epicrisis();
            epicrisis.setInfo(examinationDoctorDto.getEpicrisis());
            epicrisis.setAppointment(appointment);
            User patient = entityManager.find(User.class, examinationDoctorDto.getPatientIdDto());
            User doctor = entityManager.find(User.class, examinationDoctorDto.getIdDoctor());
            AppointmentUsers appointmentUsers = new AppointmentUsers();
            appointmentUsers.setAppointment(appointment);
            appointmentUsers.setDoctor(doctor);
            appointmentUsers.setPatient(patient);
            entityManager.persist(appointment);
            entityManager.persist(appointmentUsers);
            entityManager.persist(epicrisis);
        }
        Diagnosis diagnosis = new Diagnosis(examinationDoctorDto.getDiagnosisDto());
        PatientDetails patientDetails = IPatientDetailsService.getPatientDetailsById(examinationDoctorDto.getPatientIdDto());
        patientDetails.setStatus(CHECKING);
        DiagnosisPatient diagnosisPatient = new DiagnosisPatient();
        diagnosisPatient.setPatientDetails(patientDetails);
        diagnosisPatient.setDiagnosis(diagnosis);
        entityManager.persist(diagnosis);
        entityManager.merge(patientDetails);
        entityManager.persist(diagnosisPatient);
    }

    @Override
    public List<AppointmentUsers> getAppointmentUsers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AppointmentUsers> cr = cb.createQuery(AppointmentUsers.class);
        return entityManager.createQuery(cr.select(cr.from(AppointmentUsers.class))).getResultList();

    }

    @Override
    public void deleteAppointmentUsers(Integer number) {
        entityManager.remove(new AppointmentUsers());
    }
}
