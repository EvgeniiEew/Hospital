package by.home.hospital.service.impl;

import by.home.hospital.domain.Credentials;
import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.User;
import by.home.hospital.dto.PatientWhisStatusDto;
import by.home.hospital.enums.PatientStatus;
import by.home.hospital.enums.Position;
import by.home.hospital.service.IPatientDetailsService;
import by.home.hospital.service.repository.PatientDitalesjpaRepository;
import by.home.hospital.service.repository.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static by.home.hospital.enums.PatientStatus.*;

@Transactional
@Service
public class PatientDetailsService implements IPatientDetailsService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    PatientDitalesjpaRepository patientDitalesjpaRepository;

    @Override
    public void addPatientDetails(PatientDetails patientDetails) {
        entityManager.persist(patientDetails);
    }

    @Override
    public List<PatientDetails> getPatientDetails() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatientDetails> cr = cb.createQuery(PatientDetails.class);
        return entityManager.createQuery(cr.select(cr.from(PatientDetails.class))).getResultList();
    }

    @Override
    public PatientDetails getPatientDetailsById(int id) {
        Query query = entityManager.createQuery("Select u from PatientDetails u WHERE patient_id = :id", PatientDetails.class);
        query.setParameter("id", id);
        List<PatientDetails> patientDetailsList = query.getResultList();
        return patientDetailsList.get(0);
    }

    public PatientDetails getPatientDetaisByIdUser(int idUser) {
        return this.patientDitalesjpaRepository.findById(idUser);
    }

    @Override
    public void deletePatientDetails(Integer number) {
        entityManager.remove(new PatientDetails());
    }

    //изменить статус пациента на RECEPTION_PENDING в ожидании приема
    @Override
    public void patientStatusСhange(Integer number) {
        PatientDetails patientDetails = getPatientDetaisByIdUser(number);
        patientDetails.setPatientStatus(RECEPTION_PENDING);
        addPatientDetails(patientDetails);
    }

    public List<PatientWhisStatusDto> getPatientsNotExaminded() {
        return this.getPatientWithStatus(NOT_EXAMINED);
    }

    public List<PatientWhisStatusDto> getCheckoutPatient() {
        return this.getPatientWithStatus(CHECKOUT);
    }

        //записаны на прием в ожиданнии назначения
    public List<PatientWhisStatusDto> getReceptionPendingPatient() {
        return this.getPatientWithStatus(RECEPTION_PENDING);
    }


    public List<PatientWhisStatusDto> getCheckingPatient() {
        return this.getPatientWithStatus(CHECKING);
    }

    public List<PatientWhisStatusDto> getPatientWithStatus(PatientStatus status) {
        HashSet<PatientDetails> patientDetails = patientDitalesjpaRepository.findAllByStatus(status);
//        List<PatientWhisStatusDto> patientWhisStatusDtos = patientDetails.stream().map(patientDetails1 -> new PatientWhisStatusDto(
//                patientDetails1.getId(),
//                patientDetails1.getStatus()
//        )).collect(Collectors.toList());
//        return patientWhisStatusDtos;
        List<PatientWhisStatusDto> patientWhisStatusDtos = patientDetails.stream().map(patientDetails1 -> {
            User user = patientDetails1.getPatient();
            return new PatientWhisStatusDto(
                    patientDetails1.getId(),
                    user.getCredentials().getFirstName(),
                    user.getCredentials().getLastName(),
                    patientDetails1.getStatus());

        }).collect(Collectors.toList());
        return patientWhisStatusDtos;
    }
}
