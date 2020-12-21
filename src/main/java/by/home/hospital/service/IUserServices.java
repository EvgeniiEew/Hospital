package by.home.hospital.service;

import by.home.hospital.domain.User;

import java.util.List;

public interface IUserServices {
    List<User> getUsers();

    void addUser(User user);

    void deleteUser(Integer number);

}
