package by.home.hospital.domain;

import by.home.hospital.dto.AppointmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExaminationDoctor {
    private final int patientId;
    private final int idDoctor;
    private final String diagnosis;
    private final AppointmentDto appointmentDto;
    private final String epicrisis;

}
