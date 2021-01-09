package by.home.hospital.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PersonFormDto {

    @NotNull
    @Size(min = 6, max = 30)
    private String login;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;

    public PersonFormDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
