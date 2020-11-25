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
    @JoinColumn(name = "patient_id")
    private User patient;

//
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;
//

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}