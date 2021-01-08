package by.home.hospital.service.repository;

import by.home.hospital.domain.User;
import by.home.hospital.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;

public interface UserJpaRepo extends JpaRepository<User, Integer> {
    HashSet<User> findAllByPositionOrderByFirstNameDesc(Position position);

    User getUserByCredentials_Id(Integer id);

    List<User> findByOrderByFirstNameAsc();

    User getUserById(Integer Id);

//    select u.id
//    from users u
//    inner join appointment_users au on u.id = au.patient_id
//    inner join appointment a on au.appointment_id = a.id
//    where 'DONE' = all (select status
//                    from appointment ap
//                            inner join appointment_users au2 on ap.id = au2.appointment_id
//                            where au2.patient_id = u.id);
}