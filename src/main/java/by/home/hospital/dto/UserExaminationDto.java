package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import lombok.Data;

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
