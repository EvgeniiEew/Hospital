package by.home.hospital.service.impl;

import by.home.hospital.domain.Credentials;
import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.User;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.service.ICredentialsService;
import by.home.hospital.service.repository.CredentialsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.home.hospital.enums.PatientStatus.NOT_EXAMINED;
import static by.home.hospital.enums.Position.PATIENT;

@Transactional
@Service
public class CredentialsService implements ICredentialsService {

    @Autowired
    private PatientDetailsService patientDetailsService;

    @Autowired
    private UserService userService;
    @Autowired
    private CredentialsJpaRepository credentialsJpaRepository;

    private Credentials createCredentials(PatientRegisterDto patientRegisterDto){
        Credentials creds1 = new Credentials();
        creds1.setLogin(patientRegisterDto.getLogin());
        creds1.setPassword(patientRegisterDto.getPassword());
        this.credentialsJpaRepository.save(creds1);
        return creds1;
    }

    private  User createUser(PatientRegisterDto patientRegisterDto){
        User user = new User();
        user.setPosition(PATIENT);
        user.setFirstName(patientRegisterDto.getFirstName());
        user.setLastName(patientRegisterDto.getLastName());
        user.setCredentials(this.createCredentials(patientRegisterDto));
        this.userService.save(user);
        return user;
    }
    @Override
    public void registerPatient(PatientRegisterDto patientRegisterDto) {
        PatientDetails patientDetails = new PatientDetails();
        patientDetails.setPatientStatus(NOT_EXAMINED);
        patientDetails.setPatient(this.createUser(patientRegisterDto));
        this.patientDetailsService.savePatientDetails(patientDetails);
    }

    @Override
    public List<Credentials> getCredentials() {
        return this.credentialsJpaRepository.findAll();
    }

    public void saveAndFlush(Credentials credentials) {
        this.credentialsJpaRepository.saveAndFlush(credentials);
    }

    @Override
    public void deleteCredentials(Integer number) {
        this.credentialsJpaRepository.deleteById(number);
    }

    @Override
    public Optional<Credentials> findByLogin(String credentialLogin) {
        return this.credentialsJpaRepository.findByLogin(credentialLogin);
    }

    public Credentials saveCredentialsFromDoctorRegisterDto(DoctorRegisterDto doctorRegisterDto){
        Credentials credentials = new Credentials();
        credentials.setLogin(doctorRegisterDto.getLogin());
        credentials.setPassword(doctorRegisterDto.getPassword());
       return this.credentialsJpaRepository.save(credentials);
    }

}
