package by.home.hospital.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credentials")
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Length(min = 6, max = 20)
    @Column(nullable = false, unique = true, updatable = false)
    private String login;

    @Length(min = 6, max = 20)
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "credentials", fetch = FetchType.LAZY)
    private User user;

}
