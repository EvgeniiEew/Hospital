package by.home.hospital.service;

import by.home.hospital.domain.PatientDetails;

import java.util.List;

public interface PatientDetailsRepository {
    List<PatientDetails> getPatientDetails();

    void addPatientDetails(PatientDetails patientDetails);

    PatientDetails getPatientDetailsById(int id);

    void deletePatientDetails(Integer number);
}
