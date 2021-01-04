package by.home.hospital.service.impl;

import by.home.hospital.domain.Credentials;
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

    public Credentials createCredentialsFromPatientRegisterDto(PatientRegisterDto patientRegisterDto) {
        Credentials credentials = new Credentials();
        credentials.setLogin(patientRegisterDto.getLogin());
        credentials.setPassword(patientRegisterDto.getPassword());
        return this.credentialsJpaRepository.save(credentials);
    }

    public Credentials saveCredentialsFromDoctorRegisterDto(DoctorRegisterDto doctorRegisterDto) {
        Credentials credentials = new Credentials();
        credentials.setLogin(doctorRegisterDto.getLogin());
        credentials.setPassword(doctorRegisterDto.getPassword());
        return this.credentialsJpaRepository.save(credentials);
    }

    public Credentials saveCredentialsFromNurseRegisterDto(NurseRegisterDto nurseRegisterDto){
        Credentials credentials = new Credentials();
        credentials.setLogin(nurseRegisterDto.getLogin());
        credentials.setPassword(nurseRegisterDto.getPassword());
        return this.credentialsJpaRepository.save(credentials);
    }
    @Override
    public List<Credentials> getCredentials() {
        return this.credentialsJpaRepository.findAll();
    }

    @Override
    public void deleteCredentials(Integer number) {
        this.credentialsJpaRepository.deleteById(number);
    }

    @Override
    public Optional<Credentials> findByLogin(String credentialLogin) {
        return this.credentialsJpaRepository.findByLogin(credentialLogin);
    }



}
