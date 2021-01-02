package by.home.hospital.domain;

import by.home.hospital.enums.PatientStatus;
import by.home.hospital.enums.Position;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;


//public class PatientDetails {
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="User_id")
@Table(name = "Patient_Details")
public class PatientDetails extends  User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Integer id;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "patientDetails", cascade = CascadeType.REMOVE)
    private List<DiagnosisPatient> diagnosisPatients;

    public PatientDetails(String firstName, String lastName, Position position,
                         String avatarFileName, PatientStatus status ,Credentials credentials) {
        super(firstName, lastName, position, avatarFileName,  credentials);
        this.status = status;
    }
}
