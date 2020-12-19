package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//дто для выполнения назначений и возрата результатов в базу;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentFulfillmentDto {

    private Integer idApointment;
    private String infoAppointment;
    private String type;
    private String status;
//    private String info_epicris;
}
