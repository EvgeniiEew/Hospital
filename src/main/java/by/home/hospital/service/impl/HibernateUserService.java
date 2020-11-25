package by.home.hospital.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import by.home.hospital.domain.User;
import by.home.hospital.service.UserRepository;
import by.home.hospital.service.until.ISessionProvider;
import org.hibernate.Session;
import org.springframework.stereotype.Service;


@Service
public class HibernateUserService implements UserRepository {

    private ISessionProvider sessionProvider;

    public HibernateUserService(ISessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    @Override
    public void addUser(User user) {
        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<User> getUsers() {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cr = cb.createQuery(User.class);

        return entityManager.createQuery(cr.select(cr.from(User.class))).getResultList();

    }

    @Override
    public void deleteUser(Integer number) {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(new User());
        entityManager.getTransaction().commit();

    }
}
