package by.home.hospital.dto;

import by.home.hospital.domain.Diagnosis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Dto для выполнения назначений(операций.процедур.лекарств)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakingAppointmentsDto {
    private Integer idApointment;
    private Integer idPatientDitales;
    private String infoAppointment;
    private String type;
    private String status;
    private String info_epicris;
    private List<Diagnosis> diagnoses;

}
