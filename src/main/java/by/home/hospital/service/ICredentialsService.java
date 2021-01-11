package by.home.hospital.service;

import by.home.hospital.domain.Credential;

import java.util.List;
import java.util.Optional;

public interface ICredentialsService {
    List<Credential> getCredentials();

    void deleteCredentials(Integer number);

    Optional<Credential> findByEmail(String credentialEmail);

}
