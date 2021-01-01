package by.home.hospital.service.repository;

import by.home.hospital.domain.Appointment;
import by.home.hospital.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppoitmentJpaRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAll();

    List<Appointment> findAllByStatus(AppointmentStatus status);

    Appointment getById(Integer id);

    Appointment getOne(Integer id);

    Appointment save(Appointment appointment);
}
