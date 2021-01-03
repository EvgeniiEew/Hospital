//package by.home.hospital.service.impl;
//
//import by.home.hospital.domain.User;
//import by.home.hospital.dto.DoctorRegisterDto;
//import by.home.hospital.dto.PatientRegisterDto;
//import by.home.hospital.enums.Position;
//import by.home.hospital.service.IUserServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//
//import static by.home.hospital.enums.Position.PATIENT;
//
//
//@Transactional
//@Service
//public class UserService implements IUserServices {
//
//    @Autowired
//    private UserJpaRepo userJpaRepo;
//
//    @Autowired
//    private CredentialsService credentialsService;
//
//    public User getUserById(Integer id) {
//        return this.userJpaRepo.getUserById(id);
//    }
//
//    public List<User> getUsers() {
//        return this.userJpaRepo.findAll();
//    }
//    public Optional<User> findByUserName(String username){
//      return   this.userJpaRepo.findByUsername(username);
//    }
//
//    public void deleteUser(Integer number) {
//        this.userJpaRepo.deleteById(number);
//    }
//
//    public HashSet<User> findAllByPosition(Position position) {
//        return this.userJpaRepo.findAllByPosition(position);
//    }
//
//    @Override
//    public void save(User user) {
//        this.userJpaRepo.save(user);
//    }
//
//    //достать userIdDoctor из  CredentialLogin
//    public Integer getUserIdByCredentials_login(String login) {
//        Integer id = this.credentialsService.findByLogin(login).get().getId();
//        return this.userJpaRepo.getUserByCredentials_Id(id).getId();
//    }
//
//    public User saveUserFromPatientRegisterDto(PatientRegisterDto patientRegisterDto) {
//        User user = new User();
//        user.setPosition(PATIENT);
//        user.setFirstName(patientRegisterDto.getFirstName());
//        user.setLastName(patientRegisterDto.getLastName());
//        user.setCredentials(this.credentialsService.createCredentialsFromPatientRegisterDto(patientRegisterDto));
//        return this.userJpaRepo.save(user);
//    }
//
//    public User saveUserFromDoctorRegisterDto(DoctorRegisterDto doctorRegisterDto) {
//        User user = new User();
//        user.setFirstName(doctorRegisterDto.getFirstName());
//        user.setLastName(doctorRegisterDto.getLastName());
//        user.setPosition(Position.DOCTOR);
//        user.setCredentials(credentialsService.saveCredentialsFromDoctorRegisterDto(doctorRegisterDto));
//        return this.userJpaRepo.save(user);
//    }
//
//}
