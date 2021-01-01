package by.home.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// id пациента которая пришла в запросе , строка диагноз , массив назначений , Id врача достаем из сессиии .
@Data
@AllArgsConstructor
public class ExaminationDoctorDto {
    private final int patientId;
    private final int idDoctor;
    private final String diagnosis;
    private final AppointmentDto appointmentDto;
    private final String epicrisis;

}
