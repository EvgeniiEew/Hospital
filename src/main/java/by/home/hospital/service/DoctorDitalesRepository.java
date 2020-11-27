package by.home.hospital.service;

import by.home.hospital.domain.DoctorDitales;
import by.home.hospital.dto.DoctorInfoDto;

import java.util.List;

public interface DoctorDitalesRepository {

	List<DoctorInfoDto> getDoctorInfoDto();

	void addDoctorDitales(DoctorDitales doctorDitales);

	void deleteDoctorDitales(Integer number);

}
