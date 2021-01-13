package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDischarsergesDto {
    private String nameAppointment;
    private String type;
    private Data dateOfCompletion;

    private String positionDoctorNurse;
    private String firstNameDoctor;
    private String lastNameDoctor;


}
