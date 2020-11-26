package by.home.hospital.service;

import by.home.hospital.domain.Credentials;
import by.home.hospital.dto.PatientRegisterDto;

import java.util.List;

public interface CredentialsRepository {
    List<Credentials> getCredentials();

    void registerPatient(PatientRegisterDto patientRegisterDto);

    void deleteCredentials(Integer number);
}
