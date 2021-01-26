package by.home.hospital.service;

import by.home.hospital.domain.Credential;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.dto.NurseRegisterDto;
import by.home.hospital.dto.PatientRegisterDto;

import java.util.List;
import java.util.Optional;

public interface ICredentialsService {
    List<Credential> getCredentials();

    void editCredential(Credential credential);

    void deleteCredentials(Integer number);

    void save(Credential credentials);

    Optional<Credential> findByEmail(String credentialEmail);

    Credential createCredentialsFromPatientRegisterDto(PatientRegisterDto patientRegisterDto);

    Credential saveCredentialsFromDoctorRegisterDto(DoctorRegisterDto doctorRegisterDto);

    Credential saveCredentialsFromNurseRegisterDto(NurseRegisterDto nurseRegisterDto);

}
