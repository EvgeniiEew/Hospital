package by.home.hospital.service;

import by.home.hospital.domain.DiagnosisPatient;

import java.util.List;

public interface DiagnosisPatientRepository {
    List<DiagnosisPatient> getDiagnosisPatient();

    void addDiagnosisPatient(DiagnosisPatient diagnosisPatient);

    void deleteDiagnosisPatient(Integer number);
}
