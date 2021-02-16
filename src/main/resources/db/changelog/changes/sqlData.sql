
insert into credentials (id , email , password) values (1, 'Jack130a@mail.ru', 'Jack130a@mail.ru');
insert into credentials (id , email , password) values (4, 'Doctor1@mail.ru', 'Doctor1@mail.ru');
insert into credentials (id , email , password) values (7, 'Admin@mail.ru', 'Admin@mail.ru');
insert into credentials (id , email , password) values (9, 'Nurse1@mail.ru', 'Nurse1@mail.ru');
insert into credentials (id , email , password) values (11, 'Doctor2@mail.ru', 'Doctor2@mail.ru');
insert into credentials (id , email , password) values (14, 'Doctor3@mail.ru', 'Doctor3@mail.ru');
insert into credentials (id , email , password) values (17, 'Patient2@mail.ru', 'Patient2@mail.ru');
insert into credentials (id , email , password) values (20, 'Patient3@mail.ru', 'Patient3@mail.ru');

insert into  users (id , avatar_file_name , first_name , last_name , position , credential_id ) values(2,'src/main/webapp/resources/photo/siluet.jpg','Patient1FName','Patient1FName','PATIENT',1) ;
insert into  users (id , avatar_file_name , first_name , last_name , position , credential_id ) values(5,'src/main/webapp/resources/photo/1.jpg','Doctor1FName','Doctor1FName','DOCTOR',4) ;
insert into  users (id , avatar_file_name , first_name , last_name , position , credential_id ) values(8,'','Admin1FName','Admin1FName','ADMIN',7);
insert into  users (id , avatar_file_name , first_name , last_name , position , credential_id ) values(10,'','Nurse1FName','Nurse1FName','NURSE',9) ;
insert into  users (id , avatar_file_name , first_name , last_name , position , credential_id ) values(12,'src/main/webapp/resources/photo/3.jpg','Doctor2FName','Doctor2FName','DOCTOR',11) ;
insert into  users (id , avatar_file_name , first_name , last_name , position , credential_id ) values(15,'src/main/webapp/resources/photo/2.jpg','Doctor3FName','Doctor3FName','DOCTOR',14) ;
insert into  users (id , avatar_file_name , first_name , last_name , position , credential_id ) values(18,'src/main/webapp/resources/photo/siluet2.png','Patient2FName','Patient2FName','PATIENT',17) ;
insert into  users (id , avatar_file_name , first_name , last_name , position , credential_id ) values(21,'src/main/webapp/resources/photo/siluet3.png','Patient3FName','Patient3FName','PATIENT',20) ;

insert into  patient_details (id , status , patient_id ) values (3,'NOT_EXAMINED',2);
insert into  patient_details (id , status , patient_id ) values (19,'NOT_EXAMINED',18);
insert into  patient_details (id , status , patient_id ) values (22,'NOT_EXAMINED',21);

insert into doctor_ditales (id , name , doctor_id ) values (6,'Lore',5);
insert into doctor_ditales (id , name , doctor_id ) values (13,'Virologist',12);
insert into doctor_ditales (id , name , doctor_id ) values (16,'Therapist' ,15);
ALTER SEQUENCE hibernate_sequence RESTART WITH 50;