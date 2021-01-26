package by.home.hospital.service;

import by.home.hospital.domain.DiagnosisPatient;
import by.home.hospital.dto.ExaminationDoctorDto;

public interface IDiagnosisPatientService {

    DiagnosisPatient save(DiagnosisPatient diagnosisPatient);

    void saveDiagnosisPatientFromExaminationDoctorDto(ExaminationDoctorDto examinationDoctorDto);
}
