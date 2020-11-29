package by.home.hospital.service.impl;

import by.home.hospital.domain.*;
import by.home.hospital.dto.AppointmentDto;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.service.AppointmentUsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static by.home.hospital.enums.Status.CHECKING;

@Transactional
@Service
public class AppointmentUsersService implements AppointmentUsersRepository {
    @PersistenceContext
    private EntityManager entityManager;


    //todo
    @Override
    public void addAppointmentUsers(ExaminationDoctorDto examinationDoctorDto) {
        //!!! foreach для appointmentDto!! создать лист апойтментов
        AppointmentDto appointmentDto = examinationDoctorDto.getAppointmentArray().get(0); //  извлечь назначение из массива назначений
        Appointment appointment = new Appointment(appointmentDto.getName(), appointmentDto.getType(), appointmentDto.getStatus()); // создать назначение
        // статус ppointment appointment = new из енума!!!

        Diagnosis diagnosis = new Diagnosis(examinationDoctorDto.getDiagnosisDto()); //создать диагноз
        //сзодать diagnisis patient и прилинковать ему диагноз
        DiagnosisPatient diagnosisPatient = new DiagnosisPatient(new PatientDetails(examinationDoctorDto.getPatientIdDto(), CHECKING), diagnosis);
//        String p =  String.valueOf(examinationDoctorDto.getPatientIdDto());
//        String d = String.valueOf(examinationDoctorDto.getIdDoctor());
//        List patient =  ddd.createNativeQuery("SELECT * FROM public.users where id = " + p).getResultList();
//        List doctor =   ddd.createNativeQuery("SELECT * FROM public.users where id = " + d).getResultList();
        User patient = new User();
        User doctor = new User();
        patient.setId(examinationDoctorDto.getPatientIdDto());
        doctor.setId(examinationDoctorDto.getIdDoctor());
        AppointmentUsers appointmentUsers = new AppointmentUsers(patient, doctor, appointment);
        entityManager.persist(diagnosisPatient);
        entityManager.persist(appointmentUsers);

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
