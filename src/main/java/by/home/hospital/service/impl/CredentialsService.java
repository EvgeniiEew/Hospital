package by.home.hospital.service.impl;

import by.home.hospital.domain.Credentials;
import by.home.hospital.domain.User;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.service.CredentialsRepository;
import by.home.hospital.service.until.ISessionProvider;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static by.home.hospital.enums.Position.PATIENT;

@Service
public class CredentialsService implements CredentialsRepository {
    private ISessionProvider sessionProvider;

    public CredentialsService(ISessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    @Override
    public void registerPatient(PatientRegisterDto patientRegisterDto) {
        Session entityManager = sessionProvider.getEntityManager().getCurrentSession();
        entityManager.getTransaction().begin();
        User user1 = new User();
        user1.setPosition(PATIENT);
        Credentials creds1 = new Credentials();
        creds1.setFirstName(patientRegisterDto.getFirstName());
        creds1.setLastName(patientRegisterDto.getLastName());
        creds1.setLogin(patientRegisterDto.getLogin());
        creds1.setPassword(patientRegisterDto.getPassword());
        user1.setCredentials(creds1);
        entityManager.persist(creds1);
        entityManager.persist(user1);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<Credentials> getCredentials() {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Credentials> cr = cb.createQuery(Credentials.class);

        return entityManager.createQuery(cr.select(cr.from(Credentials.class))).getResultList();

    }

    @Override
    public void deleteCredentials(Integer number) {

        EntityManager entityManager = sessionProvider.getEntityManager().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(new Credentials());
        entityManager.getTransaction().commit();

    }

}
