package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExaminationDto {
    @NotNull
    @Size(min = 3, max = 30)
    private String diagnosisDto;
    @NotNull
    @Size(min = 3, max = 30)
    private String epicrisis;
    @NotNull
    @Size(min = 3, max = 30)
    private String nameApointment;
    @NotNull
    @Size(min = 3, max = 30)
    private String name;
    private String idPatient;
    private Integer authenticationDoctorId;
}
