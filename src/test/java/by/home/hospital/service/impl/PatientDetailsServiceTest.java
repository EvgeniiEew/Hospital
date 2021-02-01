package by.home.hospital.service.impl;

import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static by.home.hospital.enums.PatientStatus.NOT_EXAMINED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class PatientDetailsServiceTest {
    PatientDetails patientDetails = new PatientDetails();
    User patient = new User();


    @Test
    void savePatientRegister() {
        // WHEN
        this.patientDetails.setPatientStatus(NOT_EXAMINED);
        patient.setFirstName("FNamePatient");
        patient.setLastName("LNamePatient");
        this.patientDetails.setPatient(this.patient);
        // THEN
        assertNotNull(this.patientDetails.getStatus());
        assertNotNull(this.patientDetails.getPatient().getFirstName());
        assertNotNull(this.patientDetails.getPatient().getLastName());

        assertEquals(NOT_EXAMINED, this.patientDetails.getStatus());
        assertEquals("FNamePatient", this.patientDetails.getPatient().getFirstName());
        assertEquals("LNamePatient", this.patientDetails.getPatient().getLastName());
    }
}