package by.home.hospital.service.impl;

import by.home.hospital.domain.Appointment;
import by.home.hospital.service.until.ISessionProvider;
import org.hibernate.Session;

import by.home.hospital.service.AppointmentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class HibernateAppointmentService implements AppointmentRepository{
    private ISessionProvider sessionProvider;

    public HibernateAppointmentService(ISessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    @Override
    public void addAppointment(Appointment appointment) {
        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
        entityManager.getTransaction().begin();
        entityManager.persist(appointment);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<Appointment> getAppointment() {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Appointment> cr = cb.createQuery(Appointment.class);

        return entityManager.createQuery(cr.select(cr.from(Appointment.class))).getResultList();

    }

    @Override
    public void deleteAppointment(Integer number) {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(new Appointment());
        entityManager.getTransaction().commit();

    }
}
