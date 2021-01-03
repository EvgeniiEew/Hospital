package by.home.hospital.domain;

import by.home.hospital.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "User_id")
@Table(name = "Admin_Ditales")
public class AdminDitales extends User {

    public AdminDitales(String firstName, String lastName, Position position,
                        String avatarFileName, Credentials credentials) {
        super(firstName, lastName, position, avatarFileName, credentials);
    }
}
