package by.home.hospital.service.impl;

import by.home.hospital.domain.*;
import by.home.hospital.dto.AppointmentDto;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.service.AppointmentUsersRepository;
import by.home.hospital.service.until.ISessionProvider;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static by.home.hospital.enums.Status.CHECKING;

@Service
public class AppointmentUsersService implements AppointmentUsersRepository {
    private ISessionProvider sessionProvider;

    public AppointmentUsersService(ISessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }
    //todo
    @Override
    public void addAppointmentUsers(ExaminationDoctorDto examinationDoctorDto) {
        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
        entityManager.getTransaction().begin();
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
          AppointmentUsers appointmentUsers = new AppointmentUsers(patient,doctor,appointment);
        entityManager.persist(diagnosisPatient);
         entityManager.persist(appointmentUsers);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<AppointmentUsers> getAppointmentUsers() {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AppointmentUsers> cr = cb.createQuery(AppointmentUsers.class);

        return entityManager.createQuery(cr.select(cr.from(AppointmentUsers.class))).getResultList();

    }

    @Override
    public void deleteAppointmentUsers(Integer number) {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(new AppointmentUsers());
        entityManager.getTransaction().commit();

    }

}
