package by.home.hospital.service.impl;

import by.home.hospital.domain.Appointment;
import by.home.hospital.dto.AppointmentFulfillmentDto;
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
}
