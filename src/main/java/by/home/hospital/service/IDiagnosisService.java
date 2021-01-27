package by.home.hospital.service;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.domain.ExaminationDoctor;

import java.util.List;

public interface IDiagnosisService {

    List<Diagnosis> findAll();

    Diagnosis save(Diagnosis diagnosis);

    Diagnosis createDiagnosisFromExaminationDoctorDto(ExaminationDoctor examinationDoctor);

    List<Diagnosis> findByDiagnosisDetails_Id(Integer idPatient);
}
