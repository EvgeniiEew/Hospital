package by.home.hospital.service.repository;

import by.home.hospital.domain.DiagnosisPatient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisPatientJpaRepository extends JpaRepository<DiagnosisPatient, Integer> {
    List<DiagnosisPatient> getDiagnosisPatientByPatientDetailsId(Integer id);

    List<DiagnosisPatient> findAllById(Integer id);

    DiagnosisPatient save(DiagnosisPatient diagnosisPatient);
}
