//package by.home.hospital.service.impl;
//
//import by.home.hospital.domain.DiagnosisPatient;
//import by.home.hospital.service.DiagnosisPatientRepository;
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
//public class DiagnosisPatientService implements DiagnosisPatientRepository {
//    private ISessionProvider sessionProvider;
//
//    public DiagnosisPatientService(ISessionProvider sessionProvider) {
//        this.sessionProvider = sessionProvider;
//    }
//
//    @Override
//    public void addDiagnosisPatient(DiagnosisPatient diagnosisPatient) {
//        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
//        entityManager.getTransaction().begin();
//        entityManager.persist(diagnosisPatient);
//        entityManager.getTransaction().commit();
//
//    }
//
//    @Override
//    public List<DiagnosisPatient> getDiagnosisPatient() {
//
//        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<DiagnosisPatient> cr = cb.createQuery(DiagnosisPatient.class);
//
//        return entityManager.createQuery(cr.select(cr.from(DiagnosisPatient.class))).getResultList();
//
//    }
//
//    @Override
//    public void deleteDiagnosisPatient(Integer number) {
//
//        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();
//
//        entityManager.getTransaction().begin();
//        entityManager.remove(new DiagnosisPatient());
//        entityManager.getTransaction().commit();
//
//    }
//}
