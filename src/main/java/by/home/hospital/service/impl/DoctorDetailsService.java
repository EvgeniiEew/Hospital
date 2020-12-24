package by.home.hospital.service.impl;

import by.home.hospital.domain.Credentials;
import by.home.hospital.domain.DoctorDetails;
import by.home.hospital.domain.User;
import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.enums.Position;
import by.home.hospital.service.IDoctorDetailsRepository;
import by.home.hospital.service.repository.CredentialsJpaRepository;
import by.home.hospital.service.repository.DoctorDitalesJpaRepository;
import by.home.hospital.service.repository.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DoctorDetailsService implements IDoctorDetailsRepository {
    @Autowired
    private   CredentialsJpaRepository credentialsJpaRepository;

    @Autowired
    private  DoctorDitalesJpaRepository doctorDitalesJpaRepository;

    @Autowired
    private   UserJpaRepo userJpaRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addDoctorDetails(DoctorDetails doctorDetails) {
        entityManager.persist(doctorDetails);
    }

    public List<DoctorInfoDto> getDoctorInfoDto() {
        HashSet<User> users = this.userJpaRepo.findAllByPosition(Position.DOCTOR);
        List<DoctorInfoDto> doctorInfoDtoList = users.stream().map(user -> {
            Credentials credentials = user.getCredentials();
            return new DoctorInfoDto(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPosition(),
                    user.getDoctorDetails().getName()
            );
        }).collect(Collectors.toList());
        return doctorInfoDtoList;
    }


    @Override
    public void registerDoctor(DoctorRegisterDto doctorRegisterDto) {
        Credentials credentials = new Credentials();
        credentials.setLogin(doctorRegisterDto.getLogin());
        credentials.setPassword(doctorRegisterDto.getPassword());
        credentialsJpaRepository.saveAndFlush(credentials);
        User user = new User();
        user.setFirstName(doctorRegisterDto.getFirstName());
        user.setLastName(doctorRegisterDto.getLastName());
        user.setPosition(Position.DOCTOR);
        user.setCredentials(credentials);
        userJpaRepo.saveAndFlush(user);
        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.setDoctor(user);
        doctorDetails.setName(doctorRegisterDto.getDoctorDitales());
        doctorDitalesJpaRepository.save(doctorDetails);
    }

    @Override
    public void deleteDoctorDetails(Integer number) {
        entityManager.remove(new DoctorDetails());
    }
}
