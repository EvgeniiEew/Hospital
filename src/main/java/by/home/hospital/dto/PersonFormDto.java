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

    public PersonFormDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
