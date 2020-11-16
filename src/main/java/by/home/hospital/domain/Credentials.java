package by.home.hospiital.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String firstName;
    private String lastName;

    @Length(min = 6, max = 20)
    @Column(nullable = false, unique = true, updatable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "credentials")
    private Patient patient;

    @OneToOne(mappedBy = "credentials")
    private Employee employee;
}
