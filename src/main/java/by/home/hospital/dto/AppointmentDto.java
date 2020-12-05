package by.home.hospital.dto;

import by.home.hospital.enums.Type;

public class AppointmentDto {

    private String name;
    private Type type;

    public AppointmentDto(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
