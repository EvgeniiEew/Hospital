package by.home.hospital.domain;

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

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "appointment" ,fetch = FetchType.LAZY)
    private AppointmentUsers appointmentUsers;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY)
    private Epicrisis epicrisis;
}
