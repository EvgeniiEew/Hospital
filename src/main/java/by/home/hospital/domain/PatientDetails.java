package by.home.hospital.domain;

import by.home.hospital.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Patient_Details")
public class PatientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Enumerated(EnumType.STRING)
    private Status status;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId", referencedColumnName = "id")
    private User patient;

    @OneToMany(mappedBy = "patientDetails", cascade = CascadeType.REMOVE)
   // @JoinColumn(name = "diagnosis_id")//
    private List<DiagnosisPatient> diagnosisPatients;

    public PatientDetails(int id, Status status) {
        this.id = id;
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDoctor(User doctor) {
        this.patient = doctor;
    }
}
