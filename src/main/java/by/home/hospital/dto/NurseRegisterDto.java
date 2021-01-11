package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NurseRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
