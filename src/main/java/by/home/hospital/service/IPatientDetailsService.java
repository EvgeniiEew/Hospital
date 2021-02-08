package by.home.hospital.service;

import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.PatientStatus;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.dto.PatientWhisStatusDto;

import java.util.List;

public interface IPatientDetailsService {

    List<PatientWhisStatusDto> getPatientWithStatus(PatientStatus status);

    List<PatientDetails> getPatientDetails();

    PatientDetails savePatientRegister(PatientRegisterDto patientRegisterDto);

    PatientDetails save(PatientDetails patientDetails);

    PatientWhisStatusDto getUserByIdPatientDetails(Integer id);

    PatientDetails getPatientDetailsById(int idPatient);

    void deletePatientDetails(Integer number);

    void patientStatusChangeToReceptionPending(Integer id);

    void PatientStatusReceptionPendingToNotExaminet(Integer id);
}
