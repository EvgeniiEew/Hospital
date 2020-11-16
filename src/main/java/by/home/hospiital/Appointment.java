package by.home.hospiital;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    // type (procedure,drug,Operation)
    //statuc (Done,pending)

    @ManyToOne
    @JoinColumn(name = "Patients_id")
    private Patients ownerPatients;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    private Employees ownerEmployees_a;
}
