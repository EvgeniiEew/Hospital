//package by.home.hospital.service;
//
//import by.home.hospital.domain.Credentials;
//import by.home.hospital.service.impl.CredentialsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Service
//public class CredentialAuthService implements UserDetailsService {
//    @Autowired
//    private CredentialsService credentialsService;
//
//    @Override
//    public UserDetails loadUserByUsername(String credentialLogin) throws UsernameNotFoundException {
//        return credentialsService.findByLogin(credentialLogin).map(credentials -> new User(credentials.getLogin(),
//                credentials.getPassword(), toAuthorities(credentials)))
//                .orElse(null);
//    }
//
//    private Collection<? extends GrantedAuthority> toAuthorities(Credentials findByUserName) {
//        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + findByUserName.getUser().getPosition()));
//    }
//
//}
