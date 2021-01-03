package by.home.hospital.service;

import by.home.hospital.domain.Credentials;
import by.home.hospital.dto.PatientRegisterDto;

import java.util.List;
import java.util.Optional;

public interface ICredentialsService {
    List<Credentials> getCredentials();

    void deleteCredentials(Integer number);

    Optional<Credentials> findByLogin(String credentialLogin);

}
