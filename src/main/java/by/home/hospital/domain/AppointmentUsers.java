package by.home.hospital.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AppointmentUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}