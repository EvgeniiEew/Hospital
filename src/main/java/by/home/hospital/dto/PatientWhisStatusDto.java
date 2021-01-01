package by.home.hospital.dto;

import by.home.hospital.enums.PatientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientWhisStatusDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private PatientStatus status;

}
