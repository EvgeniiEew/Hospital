package by.home.hospital.service.impl;

import by.home.hospital.domain.Appointment;
import by.home.hospital.domain.Epicrisis;
import by.home.hospital.dto.ExaminationDoctorDto;
import by.home.hospital.dto.ResultProcedurFormDto;
import by.home.hospital.service.IEpicrisisService;
import by.home.hospital.service.repository.EpicrisisJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class EpicrisisService implements IEpicrisisService {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private EpicrisisJpaRepository epicrisisJpaRepository;

    public Epicrisis getByAppointment_Id(Integer apointmentId) {
        return this.epicrisisJpaRepository.getByAppointment_Id(apointmentId);
    }

    public String getEpicrisesByInfo(Integer apointmentId) {
        return this.epicrisisJpaRepository.findAllActiveEpicrisisNative(apointmentId);
    }

    public void saveEpicrisFromResultProcedureDto(ResultProcedurFormDto resultProcedurFormDto) {
        Epicrisis epicrisis = getByAppointment_Id(resultProcedurFormDto.getIdAppointment());
        epicrisis.setInfo(epicrisis.getInfo().concat(" " + resultProcedurFormDto.getResaultEpicris()));
        this.epicrisisJpaRepository.save(epicrisis);
    }

    public void saveEpicrisFromExaminationDoctorDto(ExaminationDoctorDto examinationDoctorDto, Appointment appointment) {
        Epicrisis epicrisis = new Epicrisis();
        epicrisis.setInfo(examinationDoctorDto.getEpicrisis());
        epicrisis.setAppointment(appointment);
        this.epicrisisJpaRepository.save(epicrisis);
    }

    public List<Epicrisis> getEpicrisisToDiscargeList(Integer idPatient) {
        List<Appointment> appointmentsList = this.appointmentService.findAppointmentsByPatientId(idPatient);
        List<Epicrisis> epicrisisList = new ArrayList<>();
        appointmentsList.forEach(list -> {
            epicrisisList.add(new Epicrisis(
                   Integer.valueOf(list.getEpicrisis().getId()),
                    list.getEpicrisis().getInfo()
            ));
        });
        return epicrisisList;
    }

    public void save(Epicrisis epicrisis) {
        this.epicrisisJpaRepository.save(epicrisis);
    }
}
