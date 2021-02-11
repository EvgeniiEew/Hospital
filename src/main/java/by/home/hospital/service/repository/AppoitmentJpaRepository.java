package by.home.hospital.service.repository;

import by.home.hospital.domain.Appointment;
import by.home.hospital.domain.AppointmentStatus;
import by.home.hospital.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppoitmentJpaRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByOrderByTypeAsc();

    List<Appointment> findByStatusOrderByTypeAsc(AppointmentStatus status);

    List<Appointment> findByStatusAndTypeNotLike(AppointmentStatus status, Type type);

    @Query(value = "SELECT * FROM appointment a where a.id = ?1", nativeQuery = true)
    Appointment findAppointmentByIdNative(Integer id);

    @Query(value = "select * from appointment a join appointment_users au on a.id = au.appointment_id where au.patient_id =:id order by date desc;", nativeQuery = true)
    List<Appointment> findAppointmentsByPatientId(Integer id);
}
