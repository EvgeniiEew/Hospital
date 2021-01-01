package by.home.hospital.service.repository;

import by.home.hospital.domain.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CredentialsJpaRepository extends JpaRepository<Credentials, Integer> {
    Optional<Credentials> findByLogin(String credentialLogin);

    List<Credentials> findAll();

    void deleteById(Integer id);
}
