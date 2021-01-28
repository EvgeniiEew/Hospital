package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultProcedurFormDto {
    @NotNull
    @Size(min = 3, max = 30)
    private String resaultEpicris;
    private Integer idPatientUser;
    private Integer idAppointment;
}
