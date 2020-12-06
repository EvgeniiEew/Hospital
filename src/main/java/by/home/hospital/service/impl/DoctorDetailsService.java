package by.home.hospital.service.impl;

import by.home.hospital.domain.Credentials;
import by.home.hospital.domain.DoctorDetails;
import by.home.hospital.domain.User;
import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.enums.Position;
import by.home.hospital.service.repository.CredentialsJpaRepository;
import by.home.hospital.service.repository.DoctorDitalesJpaRepository;
import by.home.hospital.service.repository.UserJpaRepo;
import by.home.hospital.service.IDoctorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Service
public class DoctorDetailsService implements IDoctorDetailsRepository {
    @Autowired
    CredentialsJpaRepository credentialsJpaRepository;

    @Autowired
    DoctorDitalesJpaRepository doctorDitalesJpaRepository;

    @Autowired
    UserJpaRepo userJpaRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addDoctorDetails(DoctorDetails doctorDetails) {
        entityManager.persist(doctorDetails);
    }

    @Override
    public List<DoctorInfoDto> getDoctorInfoDto() {
        return entityManager.createNativeQuery(" SELECT credentials.firstname , " +
                "credentials.lastname , users.position, doctor_ditales.name FROM credentials, " +
                "doctor_ditales , users WHERE  users.id = doctor_ditales.doctorid and" +
                " credentials.id = users.credential_id ").getResultList();
    }

    @Override
    public void registerDoctor(DoctorRegisterDto doctorRegisterDto) {
        Credentials credentials = new Credentials();
        credentials.setFirstName(doctorRegisterDto.getFirstName());
        credentials.setLastName(doctorRegisterDto.getLastName());
        credentials.setLogin(doctorRegisterDto.getLogin());
        credentials.setPassword(doctorRegisterDto.getPassword());
        credentialsJpaRepository.saveAndFlush(credentials);
        User user = new User();
        user.setPosition(Position.DOCTOR);
        user.setCredentials(credentials);
        userJpaRepo.saveAndFlush(user);
        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.setDoctor(user);
        doctorDetails.setName(doctorRegisterDto.getDoctorDitales());
//        user.setDoctorDitales(doctorDitales);
        doctorDitalesJpaRepository.save(doctorDetails);
    }

    @Override
    public void deleteDoctorDetails(Integer number) {
        entityManager.remove(new DoctorDetails());
    }
}
