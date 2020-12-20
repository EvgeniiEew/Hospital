package by.home.hospital.service.repository;

import by.home.hospital.domain.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DiagnosisJpaRepository extends JpaRepository<Diagnosis, Integer> {

    List<Diagnosis> findAll();

    
}
