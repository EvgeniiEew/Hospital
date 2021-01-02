package by.home.hospital.service.repository;

import by.home.hospital.domain.*;
import by.home.hospital.enums.PatientStatus;
import by.home.hospital.enums.Position;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporaryService implements InitializingBean {
    @Autowired
    PatientDitalesjpaRepository patientDitalesjpaRepository;
    @Autowired
    DiagnosisJpaRepository diagnosisJpaRepository;
    @Autowired
    DiagnosisPatientJpaRepository diagnosisPatientJpaRepository;
    @Autowired
    UserJpaRepo userJpaRepo;
    @Autowired
    CredentialsJpaRepository credentialsJpaRepository;
    @Autowired
    DoctorDitalesJpaRepository doctorDitalesJpaRepository;
    @Override
    public void afterPropertiesSet() throws Exception {
        Credentials credentials = new Credentials();
        credentials.setLogin("123456789");
        credentials.setPassword("12345678");

        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.setName("хирург");
        doctorDetails.setFirstName("ВАся");
        doctorDetails.setLastName("LX");
        doctorDetails.setPosition(Position.DOCTOR);
        doctorDetails.setAvatarFileName("212");
        doctorDetails.setCredentials(credentials);

        this.doctorDitalesJpaRepository.save(doctorDetails);
        this.credentialsJpaRepository.saveAndFlush(credentials);

        Credentials credentials2 = new Credentials();
        credentials2.setLogin("00000000");
        credentials2.setPassword("0000000000");

        PatientDetails patientDetails = new PatientDetails();
        patientDetails.setFirstName("Серёжа");
        patientDetails.setLastName("Пидоров");
        patientDetails.setPosition(Position.PATIENT);
        patientDetails.setAvatarFileName("313");
        patientDetails.setStatus(PatientStatus.NOT_EXAMINED);
        patientDetails.setCredentials(credentials2);

        Diagnosis diagnosis= new Diagnosis();
        diagnosis.setName("Хуй в жопе");
        DiagnosisPatient diagnosisPatient = new DiagnosisPatient();
        diagnosisPatient.setPatientDetails(patientDetails);
        diagnosisPatient.setDiagnosis(diagnosis);
        this.diagnosisJpaRepository.save(diagnosis);
        this.patientDitalesjpaRepository.save(patientDetails);
        this.diagnosisPatientJpaRepository.save(diagnosisPatient);

    }
}
