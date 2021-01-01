package by.home.hospital.service.repository;

import by.home.hospital.domain.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorDitalesJpaRepository extends JpaRepository<DoctorDetails, Integer> {

    void deleteById(Integer id);
}
