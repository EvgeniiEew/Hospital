//package by.home.hospital.service.impl;
//
//import by.home.hospital.domain.PatientDetails;
//import by.home.hospital.service.HibernateUtil;
//import by.home.hospital.service.PatientRepository;
//import org.hibernate.Session;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import java.util.List;
//
//public class PatientService implements PatientRepository {
//
//    private static PatientRepository patientService;
//
//    public static PatientRepository getService() {
//        if (patientService == null) {
//            patientService = new PatientService();
//        }
//        return patientService;
//    }
//
//    @Override
//    public void addPatient(PatientDetails patientDetails) {
//        Session entityManager = HibernateUtil.getEntityManager().getCurrentSession();
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(patientDetails);
//        entityManager.getTransaction().commit();
//
//    }
//
//    @Override
//    public List<PatientDetails> getPatient() {
//
//        EntityManager entityManager = HibernateUtil.getEntityManager().createEntityManager();
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
//    public void deletePatient(Integer number) {
//
//        EntityManager entityManager = HibernateUtil.getEntityManager().createEntityManager();
//
//        entityManager.getTransaction().begin();
//        entityManager.remove(new PatientDetails());
//        entityManager.getTransaction().commit();
//
//    }
//}
