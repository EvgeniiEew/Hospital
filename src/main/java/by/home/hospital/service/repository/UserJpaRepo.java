package by.home.hospital.service.repository;

import by.home.hospital.domain.User;
import by.home.hospital.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;

public interface UserJpaRepo extends JpaRepository<User, Integer> {
    HashSet<User> findAllByPositionOrderByFirstNameDesc(Position position);

    User getUserByCredentials_Id(Integer id);

    List<User> findByOrderByFirstNameAsc();;

    User getUserById(Integer Id);
}