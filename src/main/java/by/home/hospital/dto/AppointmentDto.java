package by.home.hospital.dto;

import by.home.hospital.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private String name;
    private Type type;


}
