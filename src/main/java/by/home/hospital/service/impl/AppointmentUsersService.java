package by.home.hospital.service.impl;

import by.home.hospital.domain.AppointmentUsers;
import by.home.hospital.service.AppointmentUsersRepository;
import by.home.hospital.service.until.ISessionProvider;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class AppointmentUsersService implements AppointmentUsersRepository {
    private ISessionProvider sessionProvider;

    public AppointmentUsersService(ISessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    @Override
    public void addAppointmentUsers(AppointmentUsers appointmentUsers) {
        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
        entityManager.getTransaction().begin();
        entityManager.persist(appointmentUsers);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<AppointmentUsers> getAppointmentUsers() {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AppointmentUsers> cr = cb.createQuery(AppointmentUsers.class);

        return entityManager.createQuery(cr.select(cr.from(AppointmentUsers.class))).getResultList();

    }

    @Override
    public void deleteAppointmentUsers(Integer number) {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(new AppointmentUsers());
        entityManager.getTransaction().commit();

    }

}
