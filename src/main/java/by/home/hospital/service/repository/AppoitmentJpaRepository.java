package by.home.hospital.service.repository;

import by.home.hospital.domain.Appointment;
import by.home.hospital.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppoitmentJpaRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByOrderByTypeAsc();

    List<Appointment> findByStatusOrderByTypeAsc(AppointmentStatus status);
    // List<Appointment> findAllByStatus(AppointmentStatus status);
    @Query(value = "SELECT * FROM appointment a where a.id = ?1" , nativeQuery = true)
    Appointment findAppointmentByIdNative(Integer id);

    Appointment getOne(Integer id);

    Appointment save(Appointment appointment);


}
