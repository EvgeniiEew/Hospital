package by.home.hospital.dto;


import by.home.hospital.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorInfoDto {

    private String firstName;
    private String lastName;
    private Position position;
    private String name;

}
