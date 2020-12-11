package by.home.hospital.dto;

import by.home.hospital.enums.PatientStatus;
import lombok.Data;

@Data
public class PatientWhisStatusDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private PatientStatus status;

    public PatientWhisStatusDto(Integer id, String firstName, String lastName, PatientStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public PatientWhisStatusDto(Integer id, PatientStatus status) {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PatientStatus getStatus() {
        return status;
    }
}
