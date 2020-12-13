package by.home.hospital.service;

import by.home.hospital.domain.PatientDetails;

import java.util.List;

public interface IPatientDetailsService {

    List<PatientDetails> getPatientDetails();

    void addPatientDetails(PatientDetails patientDetails);

    PatientDetails getPatientDetailsById(int idPatient);

    void deletePatientDetails(Integer number);

    //изменить статус пациента на RECEPTION_PENDING в ожидании приема
    void patientStatusСhange(Integer number);
}
