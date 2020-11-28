package by.home.hospital.domain;

import by.home.hospital.enums.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "credential_id", referencedColumnName = "id")
    private Credentials credentials;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "doctor", fetch = FetchType.LAZY)
    private DoctorDitales doctorDitales;


    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "doctor", fetch = FetchType.LAZY)
    private PatientDetails patientDetails;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public List<AppointmentUsers> appointmentPatient;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public List<AppointmentUsers> appointmentDoctor;

}