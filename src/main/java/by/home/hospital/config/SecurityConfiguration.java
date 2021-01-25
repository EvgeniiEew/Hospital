package by.home.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/doctor/create", "/credanchials", "/doctor/register", "user/export/{id}")
                .hasAuthority("ROLE_ADMIN").and().authorizeRequests().antMatchers("/nurse/appointment","/patient/status", "/patient/FulfillmentOfAppointments/**", "/patient/addAppointmentToTheDatabase").hasAnyAuthority("ROLE_NURSE" ,"ROLE_DOCTOR").//.exceptionHandling().accessDeniedPage("/error/403").and()
                and().authorizeRequests().antMatchers("user/export/{id}", "/patient/**")
                .hasAnyAuthority("ROLE_DOCTOR", "ROLE_ADMIN")
                .antMatchers("/error/**", "/doctors", "/login", "/patient/register", "/patient/registers", "/webjars/**", "/footer/**", "/head/**", "/navBar/**", "/scripts/**", "/", "/users/**", "/css/**", "/favicon.ico")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .permitAll();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder noop() {
        return NoOpPasswordEncoder.getInstance();
    }
}
