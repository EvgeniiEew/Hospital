package by.home.hospital.service.impl;

import by.home.hospital.domain.DoctorDetails;
import by.home.hospital.domain.User;
import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.domain.Position;
import by.home.hospital.service.IDoctorDetailsService;
import by.home.hospital.service.repository.DoctorDitalesJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DoctorDetailsService implements IDoctorDetailsService {

    @Autowired
    private DoctorDitalesJpaRepository doctorDitalesJpaRepository;

    @Autowired
    private UserService userService;

    @Override
    public void addDoctorDetails(DoctorDetails doctorDetails) {
        doctorDitalesJpaRepository.save(doctorDetails);
    }

    @Override
    public List<DoctorInfoDto> getDoctorInfoDto() {
        HashSet<User> users = userService.findAllByPositionOrderByFirstNameDesc(Position.DOCTOR);
        return users.stream().map(user -> new DoctorInfoDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPosition(),
                user.getDoctorDetails().getName()
        )).collect(Collectors.toList());
    }

    @Override
    public void registerDoctor(DoctorRegisterDto doctorRegisterDto) {
        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.setDoctor(userService.saveUserFromDoctorRegisterDto(doctorRegisterDto));
        doctorDetails.setName(doctorRegisterDto.getDoctorDitales());
        doctorDitalesJpaRepository.save(doctorDetails);
    }

    @Override
    public void deleteDoctorDetails(Integer number) {
        doctorDitalesJpaRepository.deleteById(number);
    }

    @Override
    public void save(DoctorDetails doctorDetails) {
        doctorDitalesJpaRepository.save(doctorDetails);
    }
}
