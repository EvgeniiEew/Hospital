package by.home.hospital.service.repository;

import by.home.hospital.domain.AppointmentUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentUsersJpaRepository extends JpaRepository<AppointmentUsers, Integer> {
    AppointmentUsers getAppointmentUsersByAppointmentId(Integer IdAppointment);

    AppointmentUsers save(AppointmentUsers appointmentUsers);

//    @Query("select doctor.id from appointment_users where patient_id = ?1")
//    List<Integer> getIdDoctorsFromIdPatient(Integer patient_id);
//    @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
//    User findUserByStatusAndName(Integer status, String name);
}
