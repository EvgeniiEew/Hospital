package by.home.hospital.service.impl;

import by.home.hospital.domain.DiagnosisPatient;
import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.User;
import by.home.hospital.dto.PatientWhisStatusDto;
import by.home.hospital.dto.ResultProcedurFormDto;
import by.home.hospital.enums.PatientStatus;
import by.home.hospital.service.IPatientDetailsService;
import by.home.hospital.service.repository.PatientDitalesjpaRepository;
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
    private PatientDitalesjpaRepository patientDitalesjpaRepository;

    @Override
    public void addPatientDetails(PatientDetails patientDetails) {
        entityManager.persist(patientDetails);
    }

    @Override
    public List<PatientDetails> getPatientDetails() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<PatientDetails> cr = cb.createQuery(PatientDetails.class);
        return entityManager.createQuery(cr.select(cr.from(PatientDetails.class))).getResultList();
    }

    @Override
    public PatientDetails getPatientDetailsById(int id) {
        Query query = this.entityManager.createQuery("Select u from PatientDetails u WHERE patient_id = :id", PatientDetails.class);
        query.setParameter("id", id);
        List<PatientDetails> patientDetailsList = query.getResultList();
        return patientDetailsList.get(0);
    }

    public void  save(PatientDetails patientDetails){
        this.patientDitalesjpaRepository.save(patientDetails);
    }

    public PatientWhisStatusDto getUserById(Integer id) {
        PatientDetails patientDetails = this.patientDitalesjpaRepository.findById(id).get();
        User user = patientDetails.getPatient();
        return new PatientWhisStatusDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                patientDetails.getStatus()
        );
    }

    public PatientDetails getPatientDetaisByIdUser(Integer idUser) {
        return this.patientDitalesjpaRepository.findById(idUser).get();
    }

    @Override
    public void deletePatientDetails(Integer number) {
        this.entityManager.remove(new PatientDetails());
    }

    //изменить статус пациента на RECEPTION_PENDING в ожидании приема
    @Override
    public void patientStatusСhangeToReceptionPending(Integer number) {
        PatientDetails patientDetails = getPatientDetaisByIdUser(number);
        patientDetails.setPatientStatus(RECEPTION_PENDING);
        addPatientDetails(patientDetails);
    }

    @Override
    public void PatientStatusReceptionPendingToNotExaminet(Integer number) {
        PatientDetails patientDetails = getPatientDetaisByIdUser(number);
        patientDetails.setPatientStatus(NOT_EXAMINED);
        addPatientDetails(patientDetails);
    }

    public List<PatientWhisStatusDto> getPatientWithStatus(PatientStatus status) {
        HashSet<PatientDetails> patientDetails = this.patientDitalesjpaRepository.findAllByStatus(status);
        List<PatientWhisStatusDto> patientWhisStatusDtos = patientDetails.stream().map(patientDetails1 -> {
            User user = patientDetails1.getPatient();
            return new PatientWhisStatusDto(
                    patientDetails1.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    patientDetails1.getStatus());
        }).collect(Collectors.toList());
        return patientWhisStatusDtos;
    }

    public PatientDetails getPatientDetailsByPatientId(Integer id) {
        return this.patientDitalesjpaRepository.getPatientDetailsByPatientId(id);
    }

    public void setStatusCheckoutPatientById(ResultProcedurFormDto resultProcedurFormDto) {
        PatientDetails patientDetails = this.patientDitalesjpaRepository.getPatientDetailsById(resultProcedurFormDto.getIdPatientUser());
        patientDetails.setStatus(CHECKOUT);
        PatientDetails patientDetails1 = this.patientDitalesjpaRepository.save(patientDetails);
    }


}
