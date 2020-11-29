package by.home.hospital.dto;

import lombok.Data;

import java.util.ArrayList;

// id пациента которая пришла в запросе , строка диагноз , массив назначений , Id врача достаем из сессиии .
@Data
public class ExaminationDoctorDto {
    private final int patientIdDto;
    private final int idDoctor;
    private final String diagnosisDto;
    private final ArrayList<AppointmentDto> appointmentArray;

}
