package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegisterDto {
    @NotNull
    @Size(min = 3, max = 30)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 30)
    private String lastName;
    @Email
    @NotNull
    @Size(min = 6, max = 30)
    private String email;
    @NotNull
    @Size(min = 6, max = 30)
    private String password;

}
