package by.home.hospital.service.impl;

import by.home.hospital.domain.Credential;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.dto.NurseRegisterDto;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.service.ICredentialsService;
import by.home.hospital.service.repository.CredentialsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CredentialsService implements ICredentialsService {

    @Autowired
    private CredentialsJpaRepository credentialsJpaRepository;

    @Override
    public Credential createCredentialsFromPatientRegisterDto(PatientRegisterDto patientRegisterDto) {
        Credential credentials = new Credential();
        credentials.setEmail(patientRegisterDto.getEmail());
        credentials.setPassword(patientRegisterDto.getPassword());
        return credentialsJpaRepository.save(credentials);
    }

    @Override
    public Credential saveCredentialsFromDoctorRegisterDto(DoctorRegisterDto doctorRegisterDto) {
        Credential credentials = new Credential();
        credentials.setEmail(doctorRegisterDto.getEmail());
        credentials.setPassword(doctorRegisterDto.getPassword());
        return credentialsJpaRepository.save(credentials);
    }

    @Override
    public Credential saveCredentialsFromNurseRegisterDto(NurseRegisterDto nurseRegisterDto) {
        Credential credentials = new Credential();
        credentials.setEmail(nurseRegisterDto.getEmail());
        credentials.setPassword(nurseRegisterDto.getPassword());
        return credentialsJpaRepository.save(credentials);
    }

    @Override
    public List<Credential> getCredentials() {
        return credentialsJpaRepository.findAll();
    }

    @Override
    public void deleteCredentials(Integer number) {
        credentialsJpaRepository.deleteById(number);
    }

    @Override
    public Optional<Credential> findByEmail(String credentialEmail) {
        return credentialsJpaRepository.findByEmail(credentialEmail);
    }

    @Override
    public void editCredential(Credential credential) {
        credentialsJpaRepository.save(credential);
    }

    @Override
    public void save(Credential credentials) {
        credentialsJpaRepository.save(credentials);
    }

    public boolean existsByEmail(String email) {
        return credentialsJpaRepository.existsByEmail(email);
    }
}
