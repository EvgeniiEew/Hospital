package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDischarsergeDto {

    private Integer idPatientUser;
    private String firstNamePatient;
    private String lastNamePatient;
    private  List <String> diagnosisName;
    private List<AppointmentDischarsergesDto> listDischarserge;

}
