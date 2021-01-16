package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDischarsergesDto {
    private String nameAppointment;
    private String type;
    private String dateOfCompletion;
    private String positionDoctorNurse;
    private String nameDoctorSpecific;
    private String firstNameDoctor;
    private String lastNameDoctor;

}
