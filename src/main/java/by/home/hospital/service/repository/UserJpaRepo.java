package by.home.hospital.service.repository;

import by.home.hospital.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<User, Integer> {

}