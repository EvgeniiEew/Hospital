package by.home.hospital.service.impl;

import by.home.hospital.domain.Credentials;
import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.User;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.service.ICredentialsService;
import by.home.hospital.service.IPatientDetailsService;
import by.home.hospital.service.repository.CredentialsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

import static by.home.hospital.enums.PatientStatus.NOT_EXAMINED;
import static by.home.hospital.enums.Position.PATIENT;

@Transactional
@Service
public class CredentialsService implements ICredentialsService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PatientDetailsService patientDetailsService;

    @Autowired
    private CredentialsJpaRepository credentialsJpaRepository;
//ent remove devaite
    @Override
    public void registerPatient(PatientRegisterDto patientRegisterDto) {
        User user1 = new User();
        user1.setPosition(PATIENT);
        user1.setFirstName(patientRegisterDto.getFirstName());
        user1.setLastName(patientRegisterDto.getLastName());
        Credentials creds1 = new Credentials();
        creds1.setLogin(patientRegisterDto.getLogin());
        creds1.setPassword(patientRegisterDto.getPassword());
        user1.setCredentials(creds1);
        this.entityManager.persist(creds1);
        this.entityManager.persist(user1);
        PatientDetails patientDetails = new PatientDetails();
        patientDetails.setPatientStatus(NOT_EXAMINED);
        patientDetails.setPatient(user1);
        this.patientDetailsService.addPatientDetails(patientDetails);
    }

    @Override
    public List<Credentials> getCredentials() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Credentials> cr = cb.createQuery(Credentials.class);
        return this.entityManager.createQuery(cr.select(cr.from(Credentials.class))).getResultList();

    }
    public void saveAndFlush(Credentials credentials){
        this.credentialsJpaRepository.saveAndFlush(credentials);
    }
    @Override
    public void deleteCredentials(Integer number) {
        this.entityManager.remove(new Credentials());

    }

    @Override
    public Optional<Credentials> findByLogin(String credentialLogin) {
        return this.credentialsJpaRepository.findByLogin(credentialLogin);
    }


}
