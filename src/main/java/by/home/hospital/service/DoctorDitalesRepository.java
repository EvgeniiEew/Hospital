package by.home.hospital.service;

import by.home.hospital.domain.DoctorDitales;

import java.util.List;

public interface DoctorDitalesRepository {

	List<DoctorDitales> getDoctorDitales();

	void addDoctorDitales(DoctorDitales doctorDitales);

	void deleteDoctorDitales(Integer number);

}
