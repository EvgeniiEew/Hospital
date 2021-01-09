package by.home.hospital.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull
    @Size(min = 6, max = 30)
    private String login;
    @NotNull
    @Size(min = 6, max = 30)
    private String password;

    @Override
    public String toString() {
        return "PatientRegisterDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
