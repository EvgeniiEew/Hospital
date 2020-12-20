package by.home.hospital.service.impl;

import by.home.hospital.domain.*;
import by.home.hospital.dto.AppointmentFulfillmentDto;
import by.home.hospital.dto.MakingAppointmentsDto;
import by.home.hospital.dto.ResultProcedurFormDto;
import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.service.IAppointmentService;
import by.home.hospital.service.repository.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentService implements IAppointmentService {

    @Autowired
    private DiagnosisPatientJpaRepository diagnosisPatientJpaRepository;
    @Autowired
    private PatientDitalesjpaRepository patientDitalesjpaRepository;
    @Autowired
    private UserJpaRepo userJpaRepo;
    @Autowired
    private AppoitmentJpaRepository appoitmentJpaRepository;
    @Autowired
    private EpicrisisService epicrisisService;
    @Autowired
    private AppointmentUsersJpaRepository appointmentUsersJpaRepository;

    @Override
    public List<AppointmentFulfillmentDto> findAll() {
        List<Appointment> appointmentsfromJpa = this.appoitmentJpaRepository.findAll();
        List<AppointmentFulfillmentDto> appointmentFulfillmentDtos = appointmentsfromJpa.stream().map(appointment -> new AppointmentFulfillmentDto(
                appointment.getId(),
                appointment.getName(),
                appointment.getType().toString(),
                appointment.getStatus().toString()
        )).collect(Collectors.toList());
        return appointmentFulfillmentDtos;
    }

    public List<AppointmentFulfillmentDto> findAllByStatus(AppointmentStatus status) {
        List<Appointment> appointments = this.appoitmentJpaRepository.findAllByStatus(status);
        List<AppointmentFulfillmentDto> patientWhisStatusDtos = appointments.stream().map(appointment ->
                new AppointmentFulfillmentDto(
                        appointment.getId(),
                        appointment.getName(),
                        appointment.getType().toString(),
                        appointment.getStatus().toString()
                )).collect(Collectors.toList());
        return patientWhisStatusDtos;
    }

    // заполнение формы для выполения процедур.операций.лекарств
    public MakingAppointmentsDto getFormForMakingAppointmentsDto(Integer idAppointment) {
        Epicrisis epicrisis = this.epicrisisService.getByAppointment_Id(idAppointment);
        Appointment appointment = this.appoitmentJpaRepository.getById(idAppointment);
        AppointmentUsers appointmentUsers = appointmentUsersJpaRepository.getAppointmentUsersByAppointmentId(idAppointment);
        Integer idUserPatient = appointmentUsers.getPatient().getId();
        PatientDetails patientDetails = this.patientDitalesjpaRepository.getPatientDetailsByPatientId(idUserPatient);
        DiagnosisPatient diagnosisPatient = this.diagnosisPatientJpaRepository.getDiagnosisPatientByPatientDetailsId(patientDetails.getId());
        String diagnosis = diagnosisPatient.getDiagnosis().getName();
        return new MakingAppointmentsDto(idAppointment, patientDetails.getId(), appointment.getName(), appointment.getType().toString(),
                appointment.getStatus().toString(), epicrisis.getInfo(), diagnosis);
    }

    //занесение результатов проведения процедур в базу
    public void setPendingAppointmentStatusByID(ResultProcedurFormDto resultProcedurFormDto) {
        Appointment appointment = this.appoitmentJpaRepository.getOne(resultProcedurFormDto.getIdAppointment());
        appointment.setStatus(AppointmentStatus.DONE);
        this.appoitmentJpaRepository.save(appointment);
    }

}


