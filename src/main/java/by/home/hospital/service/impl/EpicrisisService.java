package by.home.hospital.service.impl;

import by.home.hospital.domain.Appointment;
import by.home.hospital.domain.Epicrisis;
import by.home.hospital.domain.ExaminationDoctor;
import by.home.hospital.dto.ResultProcedurFormDto;
import by.home.hospital.service.IEpicrisisService;
import by.home.hospital.service.repository.EpicrisisJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class EpicrisisService implements IEpicrisisService {

    private final AppointmentService appointmentService;
    private final EpicrisisJpaRepository epicrisisJpaRepository;

    @Override
    public Epicrisis getByAppointment_Id(Integer appointmentId) {
        return this.epicrisisJpaRepository.getByAppointment_Id(appointmentId);
    }

    @Override
    public String getEpicrisesByInfo(Integer appointmentId) {
        return this.epicrisisJpaRepository.findAllActiveEpicrisisNative(appointmentId);
    }

    @Override
    public void saveEpicrisFromResultProcedureDto(ResultProcedurFormDto resultProcedurFormDto) {
        Epicrisis epicrisis = getByAppointment_Id(resultProcedurFormDto.getIdAppointment());
        epicrisis.setInfo(epicrisis.getInfo().concat(" " + resultProcedurFormDto.getResaultEpicris()));
        this.epicrisisJpaRepository.save(epicrisis);
    }

    public void saveEpicrisFromExaminationDoctorDto(ExaminationDoctor examinationDoctor, Appointment appointment) {
        Epicrisis epicrisis = new Epicrisis();
        epicrisis.setInfo(examinationDoctor.getEpicrisis());
        epicrisis.setAppointment(appointment);
        this.epicrisisJpaRepository.save(epicrisis);
    }
    @Override
    public List<Epicrisis> getEpicrisisToDiscargeList(Integer idPatient) {
        List<Appointment> appointmentsList = this.appointmentService.findAppointmentsByPatientId(idPatient);
        List<Epicrisis> epicrisisList = new ArrayList<>();
        appointmentsList.forEach(list -> {
            epicrisisList.add(new Epicrisis(
                    Integer.valueOf(list.getEpicrisis().getId()),
                    list.getEpicrisis().getInfo(),list.getEpicrisis().getAppointment()
            ));
        });
        return epicrisisList;
    }

    @Override
    public void save(Epicrisis epicrisis) {
        this.epicrisisJpaRepository.save(epicrisis);
    }
}
