package by.home.hospital.service;

import by.home.hospital.domain.Epicrisis;
import by.home.hospital.dto.ResultProcedurFormDto;

import java.util.List;

public interface IEpicrisisService {
    void save(Epicrisis epicrisis);

    List<Epicrisis> getEpicrisisToDiscargeList(Integer idPatient);

    void saveEpicrisFromResultProcedureDto(ResultProcedurFormDto resultProcedurFormDto);

    String getEpicrisesByInfo(Integer appointmentId);
    Epicrisis getByAppointment_Id(Integer appointmentId);

}
