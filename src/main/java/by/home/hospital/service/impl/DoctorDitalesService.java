package by.home.hospital.service.impl;

import by.home.hospital.domain.DoctorDitales;
import by.home.hospital.service.DoctorDitalesRepository;
import by.home.hospital.service.until.ISessionProvider;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class DoctorDitalesService implements DoctorDitalesRepository {
    private ISessionProvider sessionProvider;

    public DoctorDitalesService(ISessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    @Override
    public void addDoctorDitales(DoctorDitales doctorDitales) {
        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
        entityManager.getTransaction().begin();
        entityManager.persist(doctorDitales);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<DoctorDitales> getDoctorDitales() {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<DoctorDitales> cr = cb.createQuery(DoctorDitales.class);

        return entityManager.createQuery(cr.select(cr.from(DoctorDitales.class))).getResultList();

    }

    @Override
    public void deleteDoctorDitales(Integer number) {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(new DoctorDitales());
        entityManager.getTransaction().commit();

    }
}
