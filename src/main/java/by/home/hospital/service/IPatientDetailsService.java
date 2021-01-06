package by.home.hospital.service;

import by.home.hospital.domain.PatientDetails;

import java.util.List;

public interface IPatientDetailsService {

    List<PatientDetails> getPatientDetails();

    PatientDetails getPatientDetailsById(int idPatient);

    void deletePatientDetails(Integer number);

    //изменить статус пациента на RECEPTION_PENDING в ожидании приема
    void patientStatusСhangeToReceptionPending(Integer id);

    //сбросить статус пациента с очереди приема на запись 	NOT_EXAMINED
    void PatientStatusReceptionPendingToNotExaminet(Integer id);
}
