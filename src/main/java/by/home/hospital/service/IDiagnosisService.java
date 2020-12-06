package by.home.hospital.service;

import by.home.hospital.domain.Diagnosis;

import java.util.List;
public interface IDiagnosisService {
    List<Diagnosis> getDiagnosis();

    void addDiagnosis(Diagnosis diagnosis);

    void deleteDiagnosis(Integer number);
}
