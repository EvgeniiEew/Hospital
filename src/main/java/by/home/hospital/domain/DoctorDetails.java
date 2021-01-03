package by.home.hospital.domain;

import by.home.hospital.enums.Position;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="User_id")
@Table(name = "Doctor_Ditales")
public class DoctorDetails extends User {

    @Column(nullable = false)
    private String name;

    public DoctorDetails(String firstName, String lastName, Position position,
                         String avatarFileName, String name ,Credentials credentials) {
        super(firstName, lastName, position, avatarFileName,  credentials);
        this.name = name;
    }
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public List<AppointmentUsers> appointmentDoctor;



}
