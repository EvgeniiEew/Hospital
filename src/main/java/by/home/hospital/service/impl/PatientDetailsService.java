//package by.home.hospital.service.impl;
//
//import by.home.hospital.domain.PatientDetails;
//import by.home.hospital.service.PatientDetailsRepository;
//import by.home.hospital.service.until.ISessionProvider;
//import org.hibernate.Session;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import java.util.List;
//
//@Service
//public class PatientDetailsService implements PatientDetailsRepository {
//
//    private ISessionProvider sessionProvider;
//
//    public PatientDetailsService(ISessionProvider sessionProvider) {
//        this.sessionProvider = sessionProvider;
//    }
//
//    @Override
//    public void addPatientDetails(PatientDetails patientDetails) {
//        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
//        entityManager.getTransaction().begin();
//        entityManager.persist(patientDetails);
//        entityManager.getTransaction().commit();
//
//    }
//
//    @Override
//    public List<PatientDetails> getPatientDetails() {
//
//        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<PatientDetails> cr = cb.createQuery(PatientDetails.class);
//
//        return entityManager.createQuery(cr.select(cr.from(PatientDetails.class))).getResultList();
//
//    }
//
//    @Override
//    public void deletePatientDetails(Integer number) {
//
//        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();
//
//        entityManager.getTransaction().begin();
//        entityManager.remove(new PatientDetails());
//        entityManager.getTransaction().commit();
//
//    }
//}
