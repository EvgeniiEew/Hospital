package by.home.hospital.service.impl;

import by.home.hospital.domain.DoctorDitales;
import by.home.hospital.dto.DoctorInfoDto;
import by.home.hospital.service.DoctorDitalesRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Service
public class DoctorDitalesService implements DoctorDitalesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addDoctorDitales(DoctorDitales doctorDitales) {
        entityManager.persist(doctorDitales);

    }

    @Override
    public List<DoctorInfoDto> getDoctorInfoDto() {
        return entityManager.createNativeQuery(" SELECT credentials.firstname , " +
                "credentials.lastname , users.position, doctor_ditales.name FROM credentials, " +
                "doctor_ditales , users WHERE  users.id = doctor_ditales.doctorid and" +
                " credentials.id = users.credential_id ").getResultList();

    }

    @Override
    public void deleteDoctorDitales(Integer number) {
        entityManager.remove(new DoctorDitales());

    }
}
