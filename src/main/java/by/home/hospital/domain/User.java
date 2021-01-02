package by.home.hospital.domain;

import by.home.hospital.enums.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public  class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
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
    private Credentials credentials;

//    @EqualsAndHashCode.Exclude
//    @OneToOne(mappedBy = "doctor", fetch = FetchType.EAGER)
//    private DoctorDetails doctorDetails;
//
//
//    @EqualsAndHashCode.Exclude
//    @OneToOne(mappedBy = "patient", fetch = FetchType.EAGER)
//    private PatientDetails patientDetails;

    public User(String firstName, String lastName, Position position, String avatarFileName,Credentials credentials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.avatarFileName = avatarFileName;
        this.credentials = credentials;
    }

//    @JsonIgnore
//    @EqualsAndHashCode.Exclude
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    public List<AppointmentUsers> appointmentPatient;
//
//    @EqualsAndHashCode.Exclude
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    public List<AppointmentUsers> appointmentDoctor;



}