package by.home.hospital.service.impl;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.service.IDiagnosisService;
import by.home.hospital.service.repository.DiagnosisJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiagnosisService implements IDiagnosisService {

    @Autowired
    private DiagnosisJpaRepository diagnosisJpaRepository;

    @Override
    public List<Diagnosis> findAll() {
        return this.diagnosisJpaRepository.findAll();
    }

    public Diagnosis save(Diagnosis diagnosis) {
        return this.diagnosisJpaRepository.save(diagnosis);
    }

    public Diagnosis createDiagnosisFromExaminationDoctorDto(ExaminationDoctorDto examinationDoctorDto) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName(examinationDoctorDto.getDiagnosis());
        return this.diagnosisJpaRepository.save(diagnosis);
    }

    public List<String> findAllPatientDiagnosis(List<Integer> listId) {
        List<String> diagnisis = new ArrayList<>();
        List<Diagnosis> lists = this.diagnosisJpaRepository.findAllById(listId);
        lists.stream().map(list -> diagnisis.add(
                list.getName()
        )).collect(Collectors.toList());
        return diagnisis;
    }
}
