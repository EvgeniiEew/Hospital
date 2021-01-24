package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExaminationDto {
    private String diagnosisDto;
    private String epicrisis;
    private String nameApointment;
    private String name;
    private String idPatient;
    private Integer authenticationDoctorId;
}
