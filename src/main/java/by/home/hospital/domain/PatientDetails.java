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
//     @JoinColumn(name = "diagnosis_id")//
    private List<DiagnosisPatient> diagnosisPatients;


    public PatientDetails() {
    }

    public PatientDetails(PatientStatus status, List<DiagnosisPatient> diagnosisPatients) {
        this.status = status;
        this.diagnosisPatients = diagnosisPatients;
    }

    public PatientDetails(Integer id, String firstName, String lastName, Position position, String avatarFileName, Credentials credentials, List<AppointmentUsers> appointmentPatient, List<AppointmentUsers> appointmentDoctor, PatientStatus status, List<DiagnosisPatient> diagnosisPatients) {
        super(id, firstName, lastName, position, avatarFileName, credentials, appointmentPatient, appointmentDoctor);
        this.status = status;
        this.diagnosisPatients = diagnosisPatients;
    }



    public PatientStatus getStatus() {
        return status;
    }

    public List<DiagnosisPatient> getDiagnosisPatients() {
        return diagnosisPatients;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public void setDiagnosisPatients(List<DiagnosisPatient> diagnosisPatients) {
        this.diagnosisPatients = diagnosisPatients;
    }
}
