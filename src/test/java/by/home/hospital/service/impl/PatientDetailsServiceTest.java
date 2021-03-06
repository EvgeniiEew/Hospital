package by.home.hospital.service.impl;

import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.User;
import by.home.hospital.dto.PatientRegisterDto;
import by.home.hospital.dto.PatientWhisStatusDto;
import by.home.hospital.service.repository.PatientDitalesjpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static by.home.hospital.domain.PatientStatus.NOT_EXAMINED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class PatientDetailsServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PatientDitalesjpaRepository patientDitalesjpaRepository;

    @InjectMocks
    private PatientDetailsService patientDetailsService;

    @Test
    void savePatientRegister() {
        PatientDetails patientDetailsMock = new PatientDetails();
        patientDetailsMock.setId(1);
        patientDetailsMock.setStatus(NOT_EXAMINED);
        PatientRegisterDto patientRegisterDto = new PatientRegisterDto(
                "FNamePanient",
                "LNamePatient",
                "Email@mail.ru",
                "1234567"
        );

        Mockito.when(userService.saveUserFromPatientRegisterDto(patientRegisterDto)).thenReturn(new User());
        Mockito.when(patientDitalesjpaRepository.save(any(PatientDetails.class))).thenReturn(patientDetailsMock);

        PatientDetails patientDetails = patientDetailsService.savePatientRegister(patientRegisterDto);

        assertNotNull(patientDetails.getStatus());
        assertEquals(NOT_EXAMINED, patientDetails.getStatus());
    }

    @Test
    void getUserByIdPatientDetailsTest() {
        PatientDetails patientDetailsMock = new PatientDetails();
        patientDetailsMock.setId(1);
        patientDetailsMock.setStatus(NOT_EXAMINED);
        patientDetailsMock.setPatient(new User());

        Mockito.when(patientDitalesjpaRepository.findById(1)).thenReturn(Optional.of(patientDetailsMock));

        PatientWhisStatusDto patientWhisStatusDto = patientDetailsService.getUserByIdPatientDetails(1);

        assertNotNull(patientWhisStatusDto);
        assertEquals(NOT_EXAMINED, patientWhisStatusDto.getStatus());
    }
}