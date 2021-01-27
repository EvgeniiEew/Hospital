package by.home.hospital.service.impl;

import by.home.hospital.domain.*;
import by.home.hospital.dto.*;
import by.home.hospital.enums.AppointmentStatus;
import by.home.hospital.enums.Type;
import by.home.hospital.service.IAppointmentService;
import by.home.hospital.service.repository.AppoitmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentService implements IAppointmentService {
    @Autowired
    private DiagnosisService diagnosisService;
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
        List<Appointment> appointmentsfromJpa = this.appoitmentJpaRepository.findByOrderByTypeAsc();
        return appointmentsfromJpa.stream().map(appointment -> new AppointmentFulfillmentDto(
                appointment.getId(),
                appointment.getName(),
                appointment.getType().toString(),
                appointment.getStatus().toString()
        )).collect(Collectors.toList());
    }

    public List<AppointmentFulfillmentDto> findAllByStatus(AppointmentStatus status) {
        List<Appointment> appointments = this.appoitmentJpaRepository.findByStatusOrderByTypeAsc(status);
        return appointments.stream().map(appointment ->
                new AppointmentFulfillmentDto(
                        appointment.getId(),
                        appointment.getName(),
                        appointment.getType().toString(),
                        appointment.getStatus().toString()
                )).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentFulfillmentDto> nurseFindAllByStatus(AppointmentStatus status) {
//        String type = Type.OPERATION.toString();
        List<Appointment> appointments = this.appoitmentJpaRepository.findByStatusAndTypeNotLike(status, Type.OPERATION);
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
        Appointment appointment = this.appoitmentJpaRepository.findAppointmentByIdNative(idAppointment);
        AppointmentUsers appointmentUsers = this.appointmentUsersService.getAppointmentUsersByAppointmentId(idAppointment);
        PatientDetails patientDetails = this.patientDetailsService.getPatientDetailsByPatientId(appointmentUsers.getPatient().getId());
        List<Diagnosis> listDiagnosis = this.diagnosisService.findByDiagnosisDetails_Id(appointmentUsers.getPatient().getId());
        return new MakingAppointmentsDto(idAppointment, patientDetails.getId(), appointment.getName(), appointment.getType().toString(),
                appointment.getStatus().toString(), this.epicrisisService.getEpicrisesByInfo(idAppointment), listDiagnosis);
    }

    //занесение результатов проведения процедур в базу //?
    public void setPendingAppointmentStatusByID(ResultProcedurFormDto resultProcedurFormDto) {
        Appointment appointment = this.appoitmentJpaRepository.getOne(resultProcedurFormDto.getIdAppointment());
        appointment.setStatus(AppointmentStatus.DONE);
        appointment.setDate(new Date());
        this.appoitmentJpaRepository.save(appointment);
    }

    public Appointment save(Appointment appointment) {
        return this.appoitmentJpaRepository.save(appointment);
    }

    @Override
    public Appointment createAppointmentFormExaminationDoctorDto(ExaminationDoctor examinationDoctor) {
        AppointmentDto appointmentDto = examinationDoctor.getAppointmentDto();
        Appointment appointment = new Appointment(appointmentDto.getName(), appointmentDto.getType(),
                AppointmentStatus.PENDING);
        return this.appoitmentJpaRepository.save(appointment);
    }

    @Override
    public List<Appointment> findAppointmentsByPatientId(Integer id) {
        return this.appoitmentJpaRepository.findAppointmentsByPatientId(id);
    }

    @Override
    public List<AppointmentDischarsergesDto> getAppontmentDischarsergesDto(Integer idPatient) {
        List<Appointment> list = appoitmentJpaRepository.findAppointmentsByPatientId(idPatient);
        List<AppointmentDischarsergesDto> dtoList = new ArrayList<>();
        list.forEach(appointment -> {
            User user = appointment.getAppointmentUsers().getDoctor();
            DoctorDetails doctorDetails = user.getDoctorDetails();
            dtoList.add(new AppointmentDischarsergesDto(
                    appointment.getName(),
                    appointment.getType().toString(),
                    appointment.getDate().toString(),
                    user.getPosition().toString(),
                    doctorDetails == null ? "Nurse" : doctorDetails.getName(),
                    user.getFirstName(),
                    user.getLastName()));
        });
        return dtoList;
    }
}


