package by.home.hospital.service.impl;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.service.DiagnosisRepository;
import by.home.hospital.service.until.ISessionProvider;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class DiagnosisService implements DiagnosisRepository {
    private ISessionProvider sessionProvider;

    public DiagnosisService(ISessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    @Override
    public void addDiagnosis(Diagnosis diagnosis) {
        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
        entityManager.getTransaction().begin();
        entityManager.persist(diagnosis);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<Diagnosis> getDiagnosis() {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Diagnosis> cr = cb.createQuery(Diagnosis.class);

        return entityManager.createQuery(cr.select(cr.from(Diagnosis.class))).getResultList();

    }

    @Override
    public void deleteDiagnosis(Integer number) {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(new Diagnosis());
        entityManager.getTransaction().commit();

    }
}
