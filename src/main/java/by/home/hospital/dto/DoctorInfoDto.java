package by.home.hospital.dto;


import by.home.hospital.enums.Position;

public class DoctorInfoDto {

    private String firstName;
    private String lastName;
    private String name;
    private Position position;

    public DoctorInfoDto(String firstName, String lastName, String name, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
