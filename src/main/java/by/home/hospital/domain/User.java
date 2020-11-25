package by.home.hospital.domain;

import by.home.hospital.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Enumerated(EnumType.STRING)
    private Position position;

//    @OneToMany(mappedBy = "users")
//    private List<AppointmentUsers> appointmentUsers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credential_id", referencedColumnName = "id")
    private Credentials credentials;

    @OneToOne(mappedBy = "doctor")
    private DoctorDitales doctorDitales;

    @OneToOne(mappedBy = "patient")
    private Epicrisis epicrisis;

    @OneToOne(mappedBy = "doctor")
    private PatientDetails patientDetails;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    public List<AppointmentUsers> appointmentUsers;

//
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    public List<AppointmentUsers> appointmentDocte;

//
}