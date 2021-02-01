package by.home.hospital.service.repository;

import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.PatientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

public interface PatientDitalesjpaRepository extends JpaRepository<PatientDetails, Integer> {
    HashSet<PatientDetails> findAllByStatus(PatientStatus status);

    PatientDetails getPatientDetailsById(Integer id);

    PatientDetails getPatientDetailsByPatientId(Integer id);

}
