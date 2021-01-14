package by.home.hospital.service.impl;

import by.home.hospital.domain.User;
import by.home.hospital.dto.*;
import by.home.hospital.enums.Position;
import by.home.hospital.service.IUserServices;
import by.home.hospital.service.repository.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static by.home.hospital.enums.Position.PATIENT;


@Transactional
@Service
public class UserService implements IUserServices {

    @Autowired
    private PatientDetailsService patientDetailsService;
    @Autowired
    private DiagnosisService diagnosisService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private CredentialsService credentialsService;

    public User getUserById(Integer id) {
        return this.userJpaRepo.getUserById(id);
    }

    public List<User> getUsers() {
        return this.userJpaRepo.findByOrderByFirstNameAsc();
    }

    public HashSet<User> findAllByPositionOrderByFirstNameDesc(Position position) {
        return this.userJpaRepo.findAllByPositionOrderByFirstNameDesc(position);
    }

    public void deleteUser(Integer number) {
        this.userJpaRepo.deleteById(number);
    }

    @Override
    public Page<User> findAllActiveUsersNative(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userJpaRepo.findAllByIdIn(this.userJpaRepo.findAllActiveUsersNative(), pageable);
    }

    public HashSet<User> findAllByPosition(Position position) {
        return this.userJpaRepo.findAllByPositionOrderByFirstNameDesc(position);
    }

    @Override
    public void save(User user) {
        this.userJpaRepo.save(user);
    }

    //достать userIdDoctor из  CredentialLogin
    public Integer getUserIdByCredentials_email(String email) {
        Integer id = this.credentialsService.findByEmail(email).get().getId();
        return this.userJpaRepo.getUserByCredentials_Id(id).getId();
    }

    public User saveUserFromPatientRegisterDto(PatientRegisterDto patientRegisterDto) {
        User user = new User();
        user.setPosition(PATIENT);
        user.setFirstName(patientRegisterDto.getFirstName());
        user.setLastName(patientRegisterDto.getLastName());
        user.setCredentials(this.credentialsService.createCredentialsFromPatientRegisterDto(patientRegisterDto));
        return this.userJpaRepo.save(user);
    }

    public User saveUserFromDoctorRegisterDto(DoctorRegisterDto doctorRegisterDto) {
        User user = new User();
        user.setFirstName(doctorRegisterDto.getFirstName());
        user.setLastName(doctorRegisterDto.getLastName());
        user.setPosition(Position.DOCTOR);
        user.setCredentials(credentialsService.saveCredentialsFromDoctorRegisterDto(doctorRegisterDto));
        return this.userJpaRepo.save(user);
    }

    public void saveNurse(NurseRegisterDto nurseRegisterDto) {
        User user = new User();
        user.setFirstName(nurseRegisterDto.getFirstName());
        user.setLastName(nurseRegisterDto.getLastName());
        user.setPosition(Position.NURSE);
        user.setCredentials(credentialsService.saveCredentialsFromNurseRegisterDto(nurseRegisterDto));
        this.userJpaRepo.save(user);
    }

    public UserDischarsergeDto generateHospitalDischarge(Integer idPatient) {
        User user = this.userJpaRepo.getUserById(idPatient);
        UserDischarsergeDto userDischarsergeDto = new UserDischarsergeDto();
        userDischarsergeDto.setIdPatientUser(idPatient);
        userDischarsergeDto.setFirstNamePatient(user.getFirstName());
        userDischarsergeDto.setLastNamePatient(user.getLastName());
        userDischarsergeDto.setDiagnosisNameAndDate(this.diagnosisService.findByDiagnosisDetails_Id(this.patientDetailsService.getPatientDetailsByPatientId(idPatient).getId()));     //this.diagnosisPatientService.getAllIdDiagnosisFromListPatientDetailsId(idPatient));
        userDischarsergeDto.setListDischarserge(this.appointmentService.getAppontmentDischarsergesDto(idPatient));
        return userDischarsergeDto;
    }
}
