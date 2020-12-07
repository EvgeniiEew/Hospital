package by.home.hospital.service.impl;

import by.home.hospital.domain.PatientDetails;
import by.home.hospital.service.IPatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static by.home.hospital.enums.Status.RECEPTION_PENDING;

@Transactional
@Service
public class PatientDetailsService implements IPatientDetailsService {
    @PersistenceContext
    private EntityManager entityManager;

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

    @Override
    public void deletePatientDetails(Integer number) {
        entityManager.remove(new PatientDetails());
    }

    //изменить статус пациента на RECEPTION_PENDING в ожидании приема
    @Override
    public void patientStatusСhange(Integer number){
        PatientDetails patientDetails = getPatientDetailsById(number);
        patientDetails.setStatus(RECEPTION_PENDING);
        addPatientDetails(patientDetails);
    }
}
