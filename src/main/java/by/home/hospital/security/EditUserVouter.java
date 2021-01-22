package by.home.hospital.security;

import by.home.hospital.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

@Component("editUserVouter")
public class EditUserVouter {
    @Autowired
    private UserService userService;

    public boolean checkUserId(Authentication authentication, Integer id) {
        String email = this.userService.getUserById(id).getCredentials().getEmail();
        String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();

        return username.equals(email);
    }
}