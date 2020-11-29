//package by.home.hospital.service.impl;
//
//import by.home.hospital.domain.Epicrisis;
//import by.home.hospital.service.EpicrisisRepository;
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
//public class EpicrisisService implements EpicrisisRepository {
//
//    private ISessionProvider sessionProvider;
//
//    public EpicrisisService(ISessionProvider sessionProvider) {
//        this.sessionProvider = sessionProvider;
//    }
//
//    @Override
//    public void addEpicrisis(Epicrisis epicrisis) {
//        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
//        entityManager.getTransaction().begin();
//        entityManager.persist(epicrisis);
//        entityManager.getTransaction().commit();
//
//    }
//
//    @Override
//    public List<Epicrisis> getEpicrisis() {
//
//        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<Epicrisis> cr = cb.createQuery(Epicrisis.class);
//
//        return entityManager.createQuery(cr.select(cr.from(Epicrisis.class))).getResultList();
//
//    }
//
//    @Override
//    public void deleteEpicrisis(Integer number) {
//
//        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();
//
//        entityManager.getTransaction().begin();
//        entityManager.remove(new Epicrisis());
//        entityManager.getTransaction().commit();
//
//    }
//}
