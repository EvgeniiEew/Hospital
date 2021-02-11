package by.home.hospital.service.repository;

import by.home.hospital.domain.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DiagnosisJpaRepository extends JpaRepository<Diagnosis, Integer> {

    @Query(value = "select d.id, name, date from diagnosis d inner join diagnosis_patient dp on d.id = dp.diagnosis_id where dp.patient_details_id = ?1 order by date desc ", nativeQuery = true)
    List<Diagnosis> findByDiagnosisPatientsId(Integer id);

}