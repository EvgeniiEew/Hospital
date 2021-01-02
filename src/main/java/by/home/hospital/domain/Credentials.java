package by.home.hospital.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Length(min = 6, max = 20)
    @Column(nullable = false, unique = true, updatable = false)
    private String login;

    @Length(min = 6, max = 20)
    @Column(nullable = false)
    private String password;
}
