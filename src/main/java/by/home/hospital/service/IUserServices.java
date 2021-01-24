package by.home.hospital.service;

import by.home.hospital.domain.User;
import by.home.hospital.dto.UserEditDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserServices {
    List<User> getUsers();
    void userEdit(UserEditDto userEditDto);

    void save(User user);

    void deleteUser(Integer number);

    Page<User> findAllActiveUsersNative(int pageNo, int pageSize);
}
