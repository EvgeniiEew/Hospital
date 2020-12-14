package by.home.hospital.service.impl;

import java.util.List;

import by.home.hospital.domain.User;
import by.home.hospital.service.repository.UserJpaRepo;
import by.home.hospital.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService implements IUserServices {

    @Autowired
    UserJpaRepo userJpaRepo;


    public void addUser(User user) {
    }


    public List<User> getUsers() {
        return userJpaRepo.findAll();
    }


    public void deleteUser(Integer number) {

    }
}
