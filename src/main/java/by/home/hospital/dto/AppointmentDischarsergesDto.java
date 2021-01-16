package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDischarsergesDto {
    private String appointmentName;
    private String appointmentType;
    private String appointmentCompletionDate;
    private String doctorPosition;
    private String doctorType;
    private String doctorFirstName;
    private String doctorLastName;

}
