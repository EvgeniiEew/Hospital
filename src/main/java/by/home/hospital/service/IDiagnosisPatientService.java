package by.home.hospital.service;

import by.home.hospital.domain.DiagnosisPatient;
import by.home.hospital.domain.ExaminationDoctor;

public interface IDiagnosisPatientService {

    DiagnosisPatient save(DiagnosisPatient diagnosisPatient);

    void saveDiagnosisPatientFromExaminationDoctorDto(ExaminationDoctor examinationDoctor);
}
