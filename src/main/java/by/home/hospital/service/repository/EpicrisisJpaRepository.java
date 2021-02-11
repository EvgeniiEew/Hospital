package by.home.hospital.service.repository;

import by.home.hospital.domain.Epicrisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EpicrisisJpaRepository extends JpaRepository<Epicrisis, Integer> {
    Epicrisis getByAppointment_Id(Integer apointmentId);

    @Query(value = "SELECT info FROM public.epicrisis where appointment_id = ?1", nativeQuery = true)
    String findAllActiveEpicrisisNative(Integer idAppointment);
}
