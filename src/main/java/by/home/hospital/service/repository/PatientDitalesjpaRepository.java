package by.home.hospital.service.repository;

import by.home.hospital.domain.PatientDetails;
import by.home.hospital.enums.PatientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;

public interface PatientDitalesjpaRepository extends JpaRepository<PatientDetails, Integer> {
    HashSet<PatientDetails> findAllByStatus(PatientStatus status);

    PatientDetails getPatientDetailsById(Integer id);

    PatientDetails getPatientDetailsByPatientId(Integer id);

    PatientDetails save(PatientDetails patientDetails);

    void deleteById(Integer id);

    List<PatientDetails> findAll();
//    findByIdOrderByFirstNameAsc

}
