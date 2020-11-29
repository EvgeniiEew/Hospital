package by.home.hospital.service.impl;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import by.home.hospital.domain.User;
import by.home.hospital.service.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("Select u from User u", User.class).getResultList();
    }

    @Override
    public void deleteUser(Integer number) {


        entityManager.getTransaction().begin();
        entityManager.remove(new User());
        entityManager.getTransaction().commit();

    }
}
