package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

@Data
@AllArgsConstructor
@NoArgsConstructor
///
public class UserExaminationDto {
    private String diagnosisDto;
    private String epicrisis;
    private String nameApointment;
    private String name;
    private String idPatient;
    private Authentication authentication;
}
