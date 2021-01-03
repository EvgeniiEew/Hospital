package by.home.hospital.service;

import by.home.hospital.domain.DoctorDetails;
import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.dto.DoctorRegisterDto;

import java.util.List;

public interface IDoctorDetailsRepository {

    List<DoctorInfoDto> getDoctorInfoDto();

    void registerDoctor(DoctorRegisterDto doctorRegisterDto);

    void addDoctorDetails(DoctorDetails doctorDetails);

    void deleteDoctorDetails(Integer number);
}
