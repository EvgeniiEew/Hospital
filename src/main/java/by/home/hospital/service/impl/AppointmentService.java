package by.home.hospital.service.impl;

import by.home.hospital.domain.Appointment;
import by.home.hospital.domain.AppointmentUsers;
import by.home.hospital.domain.Epicrisis;
import by.home.hospital.domain.PatientDetails;
import by.home.hospital.dto.AppointmentFulfillmentDto;
import by.home.hospital.dto.MakingAppointmentsDto;
import by.home.hospital.dto.ResultProcedurFormDto;
import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.service.IAppointmentService;
import by.home.hospital.service.repository.AppoitmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentService implements IAppointmentService {

    @Autowired
    private DiagnosisPatientService diagnosisPatientService;
    @Autowired
    private PatientDetailsService patientDetailsService;
    @Autowired
    private AppoitmentJpaRepository appoitmentJpaRepository;
    @Autowired
    private EpicrisisService epicrisisService;
    @Autowired
    private AppointmentUsersService appointmentUsersService;

    @Override
    public List<AppointmentFulfillmentDto> findAll() {
        List<Appointment> appointmentsfromJpa = this.appoitmentJpaRepository.findAll();
        return appointmentsfromJpa.stream().map(appointment -> new AppointmentFulfillmentDto(
                appointment.getId(),
                appointment.getName(),
                appointment.getType().toString(),
                appointment.getStatus().toString()
        )).collect(Collectors.toList());
    }

    public List<AppointmentFulfillmentDto> findAllByStatus(AppointmentStatus status) {
        List<Appointment> appointments = this.appoitmentJpaRepository.findAllByStatus(status);
        return appointments.stream().map(appointment ->
                new AppointmentFulfillmentDto(
                        appointment.getId(),
                        appointment.getName(),
                        appointment.getType().toString(),
                        appointment.getStatus().toString()
                )).collect(Collectors.toList());
    }

    // заполнение формы для выполения процедур.операций.лекарств
    public MakingAppointmentsDto getFormForMakingAppointmentsDto(Integer idAppointment) {
        Epicrisis epicrisis = this.epicrisisService.getByAppointment_Id(idAppointment);
        Appointment appointment = this.appoitmentJpaRepository.getById(idAppointment);
        AppointmentUsers appointmentUsers = this.appointmentUsersService.getAppointmentUsersByAppointmentId(idAppointment);
        PatientDetails patientDetails = this.patientDetailsService.getPatientDetailsByPatientId(appointmentUsers.getPatient().getId());
        String diagnosis = this.diagnosisPatientService.getDiagnosisPatient(patientDetails.getId()).getDiagnosis().getName();
        return new MakingAppointmentsDto(idAppointment, patientDetails.getId(), appointment.getName(), appointment.getType().toString(),
                appointment.getStatus().toString(), epicrisis.getInfo(), diagnosis);
    }

    //занесение результатов проведения процедур в базу //?
    public void setPendingAppointmentStatusByID(ResultProcedurFormDto resultProcedurFormDto) {
        Appointment appointment = this.appoitmentJpaRepository.getOne(resultProcedurFormDto.getIdAppointment());
        appointment.setStatus(AppointmentStatus.DONE);
        this.appoitmentJpaRepository.save(appointment);
    }

    public Appointment save(Appointment appointment) {
        return this.appoitmentJpaRepository.save(appointment);
    }

}


