package by.home.hospital.service.impl;

import by.home.hospital.domain.Epicrisis;
import by.home.hospital.dto.ResultProcedurFormDto;
import by.home.hospital.service.IEpicrisisService;
import by.home.hospital.service.repository.EpicrisisJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EpicrisisService implements IEpicrisisService {
    @Autowired
    private EpicrisisJpaRepository epicrisisJpaRepository;

    public Epicrisis getByAppointment_Id(Integer apointmentId) {
        return this.epicrisisJpaRepository.getByAppointment_Id(apointmentId);
    }

    public void saveEpicris(ResultProcedurFormDto resultProcedurFormDto) {
        Epicrisis epicrisis = getByAppointment_Id(resultProcedurFormDto.getIdAppointment());
        epicrisis.setInfo(epicrisis.getInfo().concat(" " +resultProcedurFormDto.getResaultEpicris()));
        this.epicrisisJpaRepository.save(epicrisis);
    }
}
