package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyViewDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String position;
    private String email;
}
