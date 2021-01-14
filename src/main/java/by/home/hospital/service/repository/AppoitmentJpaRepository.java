package by.home.hospital.service.repository;

import by.home.hospital.domain.Appointment;
import by.home.hospital.dto.AppointmentDischarsergesDto;
import by.home.hospital.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppoitmentJpaRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByOrderByTypeAsc();

    List<Appointment> findByStatusOrderByTypeAsc(AppointmentStatus status);

    @Query(value = "SELECT * FROM appointment a where a.id = ?1", nativeQuery = true)
    Appointment findAppointmentByIdNative(Integer id);

    Appointment getOne(Integer id);

    Appointment save(Appointment appointment);

    @Query(value = "select   a.name, a.type, a.date, u.position, dd.name, u.first_name, u.last_name  from  appointment a join appointment_users au on a.id = au.appointment_id and patient_id = ?1  join users u on u.id = au.doctor_id join doctor_ditales dd on dd.doctor_id = u.id order by date desc", nativeQuery = true)
    List<AppointmentDischarsergesDto> findAppointmentDischarsergesDtoByIdNative(Integer id);
}
