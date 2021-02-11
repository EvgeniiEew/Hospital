package by.home.hospital.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PersonFormDto {

    @NotNull
    @Size(min = 6, max = 30)
    private String email;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;

}
