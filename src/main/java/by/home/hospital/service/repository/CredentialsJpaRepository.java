package by.home.hospital.service.repository;

import by.home.hospital.domain.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsJpaRepository extends JpaRepository<Credentials, Integer> {

}
