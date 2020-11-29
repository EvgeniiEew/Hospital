package by.home.hospital.dto;

import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.enums.Type;

public class AppointmentDto {

    private String name;
    private Type type;
    private AppointmentStatus status; //убрать

    public AppointmentDto(String name, Type type, AppointmentStatus status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
