package by.home.hospital.service.impl;

import by.home.hospital.domain.Appointment;
import by.home.hospital.dto.AppointmentFulfillmentDto;
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
    AppoitmentJpaRepository appoitmentJpaRepository;

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
//---
    public List<AppointmentFulfillmentDto> getListAppointmentWithStatusDone() {
        return this.findAllByStatus(AppointmentStatus.DONE);
    }
//---
    public List<AppointmentFulfillmentDto> getListAppointmentWithStatusPending() {
        return this.findAllByStatus(AppointmentStatus.PENDING);
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
}


