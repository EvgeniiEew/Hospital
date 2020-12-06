package by.home.hospital.dto;

public class DoctorRegisterDto {
    private String firstName;
    private String lastName;
    private String login;
    private String password;

    private String doctorDitales;

    public DoctorRegisterDto(String firstName, String lastName, String login, String password, String doctorDitales) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.doctorDitales = doctorDitales;
    }

    public DoctorRegisterDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getDoctorDitales() {
        return doctorDitales;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDoctorDitales(String doctorDitales) {
        this.doctorDitales = doctorDitales;
    }
}
