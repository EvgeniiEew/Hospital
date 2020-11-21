//package by.home.hospital.service.impl;
//
//import by.home.hospital.domain.Credentials;
//import by.home.hospital.service.CredentialsRepository;
//import by.home.hospital.service.HibernateUtil;
//import org.hibernate.Session;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import java.util.List;
//
//public class CredentialsService implements CredentialsRepository {
//    private static CredentialsRepository credentialsService;
//
//    public static CredentialsRepository getService() {
//        if (credentialsService == null) {
//            credentialsService = new CredentialsService();
//        }
//        return credentialsService;
//    }
//
//    @Override
//    public void addCredentials(Credentials credentials) {
//        Session entityManager = HibernateUtil.getEntityManager().getCurrentSession();
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(credentials);
//        entityManager.getTransaction().commit();
//
//    }
//
//    @Override
//    public List<Credentials> getCredentials() {
//
//        EntityManager entityManager = HibernateUtil.getEntityManager().createEntityManager();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<Credentials> cr = cb.createQuery(Credentials.class);
//
//        return entityManager.createQuery(cr.select(cr.from(Credentials.class))).getResultList();
//
//    }
//
//    @Override
//    public void deleteCredentials(Integer number) {
//
//        EntityManager entityManager = HibernateUtil.getEntityManager().createEntityManager();
//
//        entityManager.getTransaction().begin();
//        entityManager.remove(new Credentials());
//        entityManager.getTransaction().commit();
//
//    }
//}
