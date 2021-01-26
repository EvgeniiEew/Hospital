package by.home.hospital.service;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.dto.ExaminationDoctorDto;

import java.util.List;

public interface IDiagnosisService {

    List<Diagnosis> findAll();

    Diagnosis save(Diagnosis diagnosis);

    Diagnosis createDiagnosisFromExaminationDoctorDto(ExaminationDoctorDto examinationDoctorDto);

    List<Diagnosis> findByDiagnosisDetails_Id(Integer idPatient);
}
