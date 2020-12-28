package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentFulfillmentDto {

    private Integer idApointment;
    private String infoAppointment;
    private String type;
    private String status;
}
