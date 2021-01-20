package by.home.hospital.domain;

import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.enums.Type;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Date date;

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public Appointment(String name, Type type, AppointmentStatus status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "appointment" ,fetch = FetchType.LAZY)
    private AppointmentUsers appointmentUsers;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY)
    private Epicrisis epicrisis;
}
