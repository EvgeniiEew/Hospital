package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResultProcedurFormDto {
    private String resaultEpicris;
    private Integer idPatientUser;
    private Integer idAppointment;
}
