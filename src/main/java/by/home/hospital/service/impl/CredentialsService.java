package by.home.hospital.service.impl;

import by.home.hospital.domain.Credential;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.dto.NurseRegisterDto;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.dto.UserEditDto;
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

    public Credential createCredentialsFromPatientRegisterDto(PatientRegisterDto patientRegisterDto) {
        Credential credentials = new Credential();
        credentials.setEmail(patientRegisterDto.getEmail());
        credentials.setPassword(patientRegisterDto.getPassword());
        return this.credentialsJpaRepository.save(credentials);
    }

    public Credential saveCredentialsFromDoctorRegisterDto(DoctorRegisterDto doctorRegisterDto) {
        Credential credentials = new Credential();
        credentials.setEmail(doctorRegisterDto.getEmail());
        credentials.setPassword(doctorRegisterDto.getPassword());
        return this.credentialsJpaRepository.save(credentials);
    }

    public Credential saveCredentialsFromNurseRegisterDto(NurseRegisterDto nurseRegisterDto) {
        Credential credentials = new Credential();
        credentials.setEmail(nurseRegisterDto.getEmail());
        credentials.setPassword(nurseRegisterDto.getPassword());
        return this.credentialsJpaRepository.save(credentials);
    }

    @Override
    public List<Credential> getCredentials() {
        return this.credentialsJpaRepository.findAll();
    }

    @Override
    public void deleteCredentials(Integer number) {
        this.credentialsJpaRepository.deleteById(number);
    }

    @Override
    public Optional<Credential> findByEmail(String credentialEmail) {
        return this.credentialsJpaRepository.findByEmail(credentialEmail);
    }

    public void editCredential(Credential credential){
       this.credentialsJpaRepository.save(credential);
    }

    public void save(Credential credentials) {
        this.credentialsJpaRepository.save(credentials);
    }
}
