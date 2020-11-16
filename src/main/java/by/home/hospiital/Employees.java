package by.home.hospiital;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ownerEmployees_a")
    private List<Appointment> appointments;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ownerEmployees_p")
    private List<Patients> patients;
}
