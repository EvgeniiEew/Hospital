package by.home.hospital.service.impl;

import by.home.hospital.domain.Credentials;
import by.home.hospital.domain.User;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.service.CredentialsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static by.home.hospital.enums.Position.PATIENT;

@Transactional
@Service
public class CredentialsService implements CredentialsRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void registerPatient(PatientRegisterDto patientRegisterDto) {
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

        //вызывать метод крединшила
    }

    @Override
    public List<Credentials> getCredentials() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Credentials> cr = cb.createQuery(Credentials.class);
        return entityManager.createQuery(cr.select(cr.from(Credentials.class))).getResultList();

    }

    @Override
    public void deleteCredentials(Integer number) {
        entityManager.remove(new Credentials());

    }

}
