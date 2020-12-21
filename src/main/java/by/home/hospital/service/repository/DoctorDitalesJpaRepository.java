package by.home.hospital.service.repository;

import by.home.hospital.domain.DoctorDetails;
import by.home.hospital.dto.DoctorInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DoctorDitalesJpaRepository extends JpaRepository<DoctorDetails, Integer> {


}
