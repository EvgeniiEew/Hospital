package by.home.hospiital;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    //statuc (null,inpection,checking,checkout)
    //diagnosisi varchar
    //final diagnosis
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ownerPatients")
    private List<Appointment> appointments;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    private Employees ownerEmployees_p;

}
