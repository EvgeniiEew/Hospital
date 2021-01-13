package by.home.hospital.dto;

import by.home.hospital.domain.Diagnosis;
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
    private  List <Diagnosis> diagnosisNameAndDate;
    private List<AppointmentDischarsergesDto> listDischarserge;

}
