package by.home.hospital.service;

import by.home.hospital.domain.User;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface IUserServices {
    List<User> getUsers();

    void save(User user);

    void deleteUser(Integer number);

    Page<User> findAllActiveUsersNative(int pageNo, int pageSize);
}
