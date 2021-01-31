package by.home.hospital.service.impl;

import by.home.hospital.domain.Credential;
import by.home.hospital.domain.DoctorDetails;
import by.home.hospital.domain.PatientDetails;
import by.home.hospital.domain.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static by.home.hospital.enums.PatientStatus.NOT_EXAMINED;
import static by.home.hospital.enums.Position.*;

@Service
@Transactional
public class InitializationClassService implements InitializingBean {
    @Autowired
    private UserService userService;
    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private DoctorDetailsService doctorDetailsService;
    @Autowired
    private PatientDetailsService patientDetailsService;


    @Override
    public void afterPropertiesSet() throws Exception {
        Credential credentials = new Credential();
        credentials.setId(1);
        credentials.setEmail("Jack130a@mail.ru");
        credentials.setPassword("Jack130a@mail.ru");
        this.credentialsService.save(credentials);

        User user = new User();
        user.setId(2);
        user.setPosition(PATIENT);
        user.setFirstName("Patient1FName");
        user.setLastName("Patient1LName");
        user.setCredentials(credentials);
        this.userService.save(user);

        PatientDetails patientDetails = new PatientDetails();
        patientDetails.setId(3);
        patientDetails.setPatientStatus(NOT_EXAMINED);
        patientDetails.setPatient(user);
        this.patientDetailsService.save(patientDetails);

        createDoctor();
        createAdmin();
        createNurse();
        createDoctor2();
        createDoctor3();

    }

    public void createDoctor() {
        Credential credentials1 = new Credential();
        credentials1.setId(4);
        credentials1.setEmail("Doctor1.@mail.ru");
        credentials1.setPassword("Doctor1.@mail.ru");
        this.credentialsService.save(credentials1);

        User user1 = new User();
        user1.setId(5);
        user1.setAvatarFileName("src/main/webapp/resources/photo/1.jpg");
        user1.setPosition(DOCTOR);
        user1.setFirstName("Doctor1FName");
        user1.setLastName("Doctor1LName");
        user1.setCredentials(credentials1);
        this.userService.save(user1);

        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.setId(6);
        doctorDetails.setDoctor(user1);
        doctorDetails.setName("Lore");
        this.doctorDetailsService.save(doctorDetails);
    }

    public void createAdmin() {
        Credential credentials2 = new Credential();
        credentials2.setId(7);
        credentials2.setEmail("Admin@mail.ru");
        credentials2.setPassword("Admin@mail.ru");
        this.credentialsService.save(credentials2);

        User user2 = new User();
        user2.setId(8);
        user2.setPosition(ADMIN);
        user2.setFirstName("Admin1FName");
        user2.setLastName("Admin1LName");
        user2.setCredentials(credentials2);
        this.userService.save(user2);

    }

    public void createNurse() {
        Credential credentials3 = new Credential();
        credentials3.setId(9);
        credentials3.setEmail("Nurse1.@mail.ru");
        credentials3.setPassword("Nurse1.@mail.ru");
        this.credentialsService.save(credentials3);

        User user3 = new User();
        user3.setId(10);
        user3.setPosition(NURSE);
        user3.setFirstName("Nurse1FName");
        user3.setLastName("Nurse1LName");
        user3.setCredentials(credentials3);
        this.userService.save(user3);
    }

    public void createDoctor2() {
        Credential credentials4 = new Credential();
        credentials4.setId(11);
        credentials4.setEmail("Doctor2.@mail.ru");
        credentials4.setPassword("Doctor2.@mail.ru");
        this.credentialsService.save(credentials4);

        User user4 = new User();
        user4.setId(12);
        user4.setAvatarFileName("src/main/webapp/resources/photo/3.jpg");
        user4.setPosition(DOCTOR);
        user4.setFirstName("Doctor2FName");
        user4.setLastName("Doctor2LName");
        user4.setCredentials(credentials4);
        this.userService.save(user4);

        DoctorDetails doctorDetails1 = new DoctorDetails();
        doctorDetails1.setId(13);
        doctorDetails1.setDoctor(user4);
        doctorDetails1.setName("Virologist");
        this.doctorDetailsService.save(doctorDetails1);
    }

    public void createDoctor3() {
        Credential credentials5 = new Credential();
        credentials5.setId(14);
        credentials5.setEmail("Doctor3.@mail.ru");
        credentials5.setPassword("Doctor3.@mail.ru");
        this.credentialsService.save(credentials5);

        User user5 = new User();
        user5.setId(15);
        user5.setAvatarFileName("src/main/webapp/resources/photo/2.jpg");
        user5.setPosition(DOCTOR);
        user5.setFirstName("Doctor3FName");
        user5.setLastName("Doctor3LName");
        user5.setCredentials(credentials5);
        this.userService.save(user5);

        DoctorDetails doctorDetails2 = new DoctorDetails();
        doctorDetails2.setId(16);
        doctorDetails2.setDoctor(user5);
        doctorDetails2.setName("Therapist");
        this.doctorDetailsService.save(doctorDetails2);
    }
}