package by.home.hospital.service.repository;

import by.home.hospital.domain.Credentials;
import by.home.hospital.domain.DoctorDetails;
import by.home.hospital.domain.User;
import by.home.hospital.enums.Position;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporaryService implements InitializingBean {
    @Autowired
    UserJpaRepo userJpaRepo;
    @Autowired
    CredentialsJpaRepository credentialsJpaRepository;
    @Autowired
    DoctorDitalesJpaRepository doctorDitalesJpaRepository;
    @Override
    public void afterPropertiesSet() throws Exception {
        Credentials credentials = new Credentials();
        credentials.setLogin("123456789");
        credentials.setPassword("12345678");

        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.setName("хирург");
        doctorDetails.setFirstName("ВАся");
        doctorDetails.setLastName("LX");
        doctorDetails.setPosition(Position.DOCTOR);
        doctorDetails.setAvatarFileName("212");
        doctorDetails.setCredentials(credentials);

        this.doctorDitalesJpaRepository.save(doctorDetails);
        this.credentialsJpaRepository.saveAndFlush(credentials);
    }
}
