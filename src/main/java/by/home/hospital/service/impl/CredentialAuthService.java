package by.home.hospital.service.impl;

import by.home.hospital.domain.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CredentialAuthService implements UserDetailsService {

    @Autowired
    private CredentialsService credentialsService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        return credentialsService.findByLogin(credentialLogin)
//                .map(credentials -> new User(credentials.getLogin(),
//                credentials.getPassword(), toAuthorities(credentials)))
//                .orElse(null);
        try {
            Credential credential = credentialsService.findByLogin(login).get();
            return new User(credential.getLogin(), credential.getPassword(), toAuthorities(credential));
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User with login not found");
        }
    }

    private Collection<? extends GrantedAuthority> toAuthorities(Credential findByUserName) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + findByUserName.getUser().getPosition()));
    }

}
