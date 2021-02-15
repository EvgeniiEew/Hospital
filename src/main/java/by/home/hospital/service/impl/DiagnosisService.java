package by.home.hospital.service.impl;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.domain.ExaminationDoctor;
import by.home.hospital.service.IDiagnosisService;
import by.home.hospital.service.repository.DiagnosisJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DiagnosisService implements IDiagnosisService {

    @Autowired
    private DiagnosisJpaRepository diagnosisJpaRepository;

    @Override
    public List<Diagnosis> findAll() {
        return diagnosisJpaRepository.findAll();
    }

    @Override
    public Diagnosis save(Diagnosis diagnosis) {
        return diagnosisJpaRepository.save(diagnosis);
    }

    @Override
    public Diagnosis createDiagnosisFromExaminationDoctorDto(ExaminationDoctor examinationDoctor) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName(examinationDoctor.getDiagnosis());
        diagnosis.setDate(new Date());
        return diagnosisJpaRepository.save(diagnosis);
    }

    @Override
    public List<Diagnosis> findByDiagnosisDetails_Id(Integer idPatient) {
        return diagnosisJpaRepository.findByDiagnosisPatientsId(idPatient);
    }

}
