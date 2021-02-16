package by.home.hospital.service.impl;

import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.PatientStatus;
import by.home.hospital.domain.User;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.dto.PatientWhisStatusDto;
import by.home.hospital.dto.ResultProcedurFormDto;
import by.home.hospital.service.IPatientDetailsService;
import by.home.hospital.service.repository.PatientDitalesjpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static by.home.hospital.domain.PatientStatus.*;

@Transactional
@Service
@RequiredArgsConstructor
public class PatientDetailsService implements IPatientDetailsService {

    private final UserService userService;

    private final PatientDitalesjpaRepository patientDitalesjpaRepository;


    @Override
    public List<PatientDetails> getPatientDetails() {
        return patientDitalesjpaRepository.findAll();
    }

    @Override
    public PatientDetails getPatientDetailsById(int id) {
        return patientDitalesjpaRepository.getPatientDetailsByPatientId(id);
    }

    @Override
    public PatientDetails savePatientRegister(PatientRegisterDto patientRegisterDto) {
        PatientDetails patientDetails = new PatientDetails();
        patientDetails.setStatus(NOT_EXAMINED);
        patientDetails.setPatient(userService.saveUserFromPatientRegisterDto(patientRegisterDto));
        return patientDitalesjpaRepository.save(patientDetails);
    }

    @Override
    public PatientDetails save(PatientDetails patientDetails) {
        return patientDitalesjpaRepository.save(patientDetails);
    }

    @Override
    public PatientWhisStatusDto getUserByIdPatientDetails(Integer id) {
        PatientDetails patientDetails = patientDitalesjpaRepository.findById(id).get();
        User user = patientDetails.getPatient();
        return new PatientWhisStatusDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                patientDetails.getStatus()
        );
    }

    public PatientDetails getPatientDetailsByIdPD(Integer idPd) {
        return patientDitalesjpaRepository.findById(idPd).get();
    }

    @Override
    public void deletePatientDetails(Integer number) {
        patientDitalesjpaRepository.deleteById(number);
    }

    @Override
    public void patientStatusChangeToReceptionPending(Integer number) {
        PatientDetails patientDetails = getPatientDetailsByIdPD(number);
        patientDetails.setStatus(RECEPTION_PENDING);
        patientDitalesjpaRepository.save(patientDetails);
    }

    @Override
    public void PatientStatusReceptionPendingToNotExaminet(Integer idNumber) {
        PatientDetails patientDetails = getPatientDetailsByIdPD(idNumber);
        patientDetails.setStatus(NOT_EXAMINED);
        patientDitalesjpaRepository.save(patientDetails);
    }

    public void resetPatientDetailslStatusFromIdUser(Integer idPatient) {
        PatientDetails patientDetails = getPatientDetailsByPatientId(idPatient);
        patientDetails.setStatus(NOT_EXAMINED);
        patientDitalesjpaRepository.save(patientDetails);
    }

    public List<PatientWhisStatusDto> getPatientWithStatus(PatientStatus status) {
        HashSet<PatientDetails> patientDetails = patientDitalesjpaRepository.findAllByStatus(status);
        List<PatientWhisStatusDto> patientWhisStatusDtos = patientDetails.stream().map(patientDetails1 -> {
            User user = patientDetails1.getPatient();
            return new PatientWhisStatusDto(
                    patientDetails1.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    patientDetails1.getStatus());
        }).collect(Collectors.toList());
        return patientWhisStatusDtos;
    }

    public PatientDetails getPatientDetailsByPatientId(Integer id) {
        return patientDitalesjpaRepository.getPatientDetailsByPatientId(id);
    }

    public void setStatusCheckoutPatientById(ResultProcedurFormDto resultProcedurFormDto) {
        PatientDetails patientDetails = patientDitalesjpaRepository.getPatientDetailsById(resultProcedurFormDto.getIdPatientUser());
        patientDetails.setStatus(CHECKOUT);
        patientDitalesjpaRepository.save(patientDetails);
    }

    public PatientDetails setStatusCheckingByPatientId(Integer id) {
        PatientDetails patientDetails = patientDitalesjpaRepository.getPatientDetailsByPatientId(id);
        patientDetails.setStatus(CHECKING);
        return patientDitalesjpaRepository.save(patientDetails);
    }

    public PatientStatus getStatusByUserId(Integer userId) {
        return patientDitalesjpaRepository.getPatientDetailsByPatientId(userId).getStatus();
    }

}
