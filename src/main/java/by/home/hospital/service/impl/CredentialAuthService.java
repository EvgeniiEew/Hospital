package by.home.hospital.service.impl;

import by.home.hospital.domain.Credential;
import by.home.hospital.domain.UserWIthId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Credential credential = credentialsService.findByEmail(email).get();
            return new UserWIthId(credential.getUser().getId(), credential.getEmail(), credential.getPassword(), toAuthorities(credential));
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User with login not found");
        }
    }

    private Collection<? extends GrantedAuthority> toAuthorities(Credential findByUserName) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + findByUserName.getUser().getPosition()));
    }

    public Integer getIdAutUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != "anonymousUser") {
            UserWIthId principal = (UserWIthId) authentication.getPrincipal();
            return principal.getId();
        }
        return 0;
    }
}
