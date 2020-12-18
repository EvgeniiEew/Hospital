package by.home.hospital.service.repository;

import by.home.hospital.domain.User;
import by.home.hospital.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

public interface UserJpaRepo extends JpaRepository<User, Integer> {
    HashSet<User> findAllByPosition(Position position);

    User getUserByCredentials_Id(Integer id);
}