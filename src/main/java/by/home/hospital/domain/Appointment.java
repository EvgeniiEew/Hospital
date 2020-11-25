package by.home.hospital.domain;

import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @OneToMany(mappedBy = "appointment")
    private List<AppointmentUsers> appointmentUsers;


}
