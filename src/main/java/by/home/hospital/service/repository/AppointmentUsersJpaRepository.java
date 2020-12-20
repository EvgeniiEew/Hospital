package by.home.hospital.service.repository;

import by.home.hospital.domain.AppointmentUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentUsersJpaRepository extends JpaRepository<AppointmentUsers, Integer> {
    AppointmentUsers getAppointmentUsersByAppointmentId(Integer IdAppointment);
}
