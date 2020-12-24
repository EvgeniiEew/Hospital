package by.home.hospital.service.impl;

import by.home.hospital.domain.DiagnosisPatient;
import by.home.hospital.service.IDiagnosisPatientService;
import by.home.hospital.service.repository.DiagnosisPatientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DiagnosisPatientService implements IDiagnosisPatientService {

    @Autowired
    private DiagnosisPatientJpaRepository diagnosisPatientJpaRepository;

    public DiagnosisPatient getDiagnosisPatient(Integer id) {
        return this.diagnosisPatientJpaRepository.getDiagnosisPatientByPatientDetailsId(id);
    }

    public DiagnosisPatient save(DiagnosisPatient diagnosisPatient) {
        return this.diagnosisPatientJpaRepository.save(diagnosisPatient);
    }

}
