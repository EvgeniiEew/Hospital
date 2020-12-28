package by.home.hospital.service.impl;

import by.home.hospital.domain.Credentials;
import by.home.hospital.domain.DoctorDetails;
import by.home.hospital.domain.User;
import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.enums.Position;
import by.home.hospital.service.IDoctorDetailsRepository;
import by.home.hospital.service.repository.DoctorDitalesJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DoctorDetailsService implements IDoctorDetailsRepository {
    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private DoctorDitalesJpaRepository doctorDitalesJpaRepository;

    @Autowired
    private UserService userService;

    @Override
    public void addDoctorDetails(DoctorDetails doctorDetails) {
        this.doctorDitalesJpaRepository.save(doctorDetails);
    }

    public List<DoctorInfoDto> getDoctorInfoDto() {
        HashSet<User> users = this.userService.findAllByPosition(Position.DOCTOR);
        return users.stream().map(user -> new DoctorInfoDto(
                user.getFirstName(),
                user.getLastName(),
                user.getPosition(),
                user.getDoctorDetails().getName()
        )).collect(Collectors.toList());
    }

//разбить
    @Override
    public void registerDoctor(DoctorRegisterDto doctorRegisterDto) {
        Credentials credentials = new Credentials();
        credentials.setLogin(doctorRegisterDto.getLogin());
        credentials.setPassword(doctorRegisterDto.getPassword());
        credentialsService.saveAndFlush(credentials);
        User user = new User();
        user.setFirstName(doctorRegisterDto.getFirstName());
        user.setLastName(doctorRegisterDto.getLastName());
        user.setPosition(Position.DOCTOR);
        user.setCredentials(credentials);
        userService.save(user);
        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.setDoctor(user);
        doctorDetails.setName(doctorRegisterDto.getDoctorDitales());
        doctorDitalesJpaRepository.save(doctorDetails);
    }

    @Override
    public void deleteDoctorDetails(Integer number) {
        this.doctorDitalesJpaRepository.deleteById(number);
    }
}
