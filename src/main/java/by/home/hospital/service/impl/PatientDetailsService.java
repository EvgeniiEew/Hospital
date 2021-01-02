//package by.home.hospital.service.impl;
//
//import by.home.hospital.domain.PatientDetails;
//import by.home.hospital.domain.User;
//import by.home.hospital.dto.PatientRegisterDto;
//import by.home.hospital.dto.PatientWhisStatusDto;
//import by.home.hospital.dto.ResultProcedurFormDto;
//import by.home.hospital.enums.PatientStatus;
//import by.home.hospital.service.IPatientDetailsService;
//import by.home.hospital.service.repository.PatientDitalesjpaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static by.home.hospital.enums.PatientStatus.*;
//
//@Transactional
//@Service
//public class PatientDetailsService implements IPatientDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PatientDitalesjpaRepository patientDitalesjpaRepository;
//
//
//    @Override
//    public List<PatientDetails> getPatientDetails() {
//        return patientDitalesjpaRepository.findAll();
//    }
//
//    @Override
//    public PatientDetails getPatientDetailsById(int id) {
//        return this.patientDitalesjpaRepository.getPatientDetailsByPatientId(id);
//    }
//
//    public void savePatientRegister(PatientRegisterDto patientRegisterDto) {
//        PatientDetails patientDetails = new PatientDetails();
//        patientDetails.setPatientStatus(NOT_EXAMINED);
//        patientDetails.setPatient(this.userService.saveUserFromPatientRegisterDto(patientRegisterDto));
//        this.patientDitalesjpaRepository.save(patientDetails);
//    }
//
//    public PatientDetails save(PatientDetails patientDetails) {
//        return this.patientDitalesjpaRepository.save(patientDetails);
//    }
//
//    public PatientWhisStatusDto getUserById(Integer id) {
//        PatientDetails patientDetails = this.patientDitalesjpaRepository.findById(id).get();
//        User user = patientDetails.getPatient();
//        return new PatientWhisStatusDto(
//                user.getId(),
//                user.getFirstName(),
//                user.getLastName(),
//                patientDetails.getStatus()
//        );
//    }
//
//    public PatientDetails getPatientDetaisByIdUser(Integer idUser) {
//        return this.patientDitalesjpaRepository.findById(idUser).get();
//    }
//
//    @Override
//    public void deletePatientDetails(Integer number) {
//        this.patientDitalesjpaRepository.deleteById(number);
//    }
//
//    @Override
//    public void patientStatusСhangeToReceptionPending(Integer number) {
//        PatientDetails patientDetails = getPatientDetaisByIdUser(number);
//        patientDetails.setPatientStatus(RECEPTION_PENDING);
//        this.patientDitalesjpaRepository.save(patientDetails);
//    }
//
//    @Override
//    public void PatientStatusReceptionPendingToNotExaminet(Integer number) {
//        PatientDetails patientDetails = getPatientDetaisByIdUser(number);
//        patientDetails.setPatientStatus(NOT_EXAMINED);
//        this.patientDitalesjpaRepository.save(patientDetails);
//    }
//
//    public List<PatientWhisStatusDto> getPatientWithStatus(PatientStatus status) {
//        HashSet<PatientDetails> patientDetails = this.patientDitalesjpaRepository.findAllByStatus(status);
//        List<PatientWhisStatusDto> patientWhisStatusDtos = patientDetails.stream().map(patientDetails1 -> {
//            User user = patientDetails1.getPatient();
//            return new PatientWhisStatusDto(
//                    patientDetails1.getId(),
//                    user.getFirstName(),
//                    user.getLastName(),
//                    patientDetails1.getStatus());
//        }).collect(Collectors.toList());
//        return patientWhisStatusDtos;
//    }
//
//    public PatientDetails getPatientDetailsByPatientId(Integer id) {
//        return this.patientDitalesjpaRepository.getPatientDetailsByPatientId(id);
//    }
//
//    public void setStatusCheckoutPatientById(ResultProcedurFormDto resultProcedurFormDto) {
//        PatientDetails patientDetails = this.patientDitalesjpaRepository.getPatientDetailsById(resultProcedurFormDto.getIdPatientUser());
//        patientDetails.setStatus(CHECKOUT);
//        this.patientDitalesjpaRepository.save(patientDetails);
//    }
//
//    public PatientDetails setStarusCheckingByPatientId(Integer id) {
//        PatientDetails patientDetails = this.patientDitalesjpaRepository.getPatientDetailsByPatientId(id);
//        patientDetails.setPatientStatus(CHECKING);
//        return this.patientDitalesjpaRepository.save(patientDetails);
//    }
//
//}
