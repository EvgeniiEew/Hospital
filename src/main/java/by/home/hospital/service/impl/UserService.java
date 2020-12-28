package by.home.hospital.service.impl;

import java.util.HashSet;
import java.util.List;

import by.home.hospital.domain.User;
import by.home.hospital.enums.Position;
import by.home.hospital.service.repository.UserJpaRepo;
import by.home.hospital.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Transactional
@Service
public class UserService implements IUserServices {

    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private CredentialsService credentialsService;

    public void addUser(User user) {
    }

    public User getUserById(Integer id) {
        return this.userJpaRepo.getUserById(id);
    }

    public List<User> getUsers() {
        return this.userJpaRepo.findAll();
    }


    public void deleteUser(Integer number) {

    }

    public HashSet<User> findAllByPosition(Position position) {
        return this.userJpaRepo.findAllByPosition(position);
    }

    public void save(User user) {
        this.userJpaRepo.save(user);
    }

    //достать userIdDoctor из  CredentialLogin
    public Integer getUserIdByCredentials_login(String login) {
        Integer id = this.credentialsService.findByLogin(login).get().getId();
        return this.userJpaRepo.getUserByCredentials_Id(id).getId();
    }

}
