package by.home.hospital.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Position position;

    private String avatarFileName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "credential_id", referencedColumnName = "id")
    private Credential credentials;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "doctor", fetch = FetchType.EAGER)
    private DoctorDetails doctorDetails;


    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "patient", fetch = FetchType.EAGER)
    private PatientDetails patientDetails;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public List<AppointmentUsers> appointmentPatient;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public List<AppointmentUsers> appointmentDoctor;

}