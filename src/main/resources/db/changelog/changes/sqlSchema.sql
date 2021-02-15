create sequence hibernate_sequence start 1 increment 1;
create table appointment (id int4 not null, date timestamp, name varchar(255), status varchar(255), type varchar(255), primary key (id));
create table appointment_users (id int4 not null, appointment_id int4, doctor_id int4, patient_id int4, primary key (id));
create table credentials (id int4 not null, email varchar(255) not null, password varchar(30) not null, primary key (id));
create table diagnosis (id int4 not null, date timestamp, name varchar(255), primary key (id));
create table diagnosis_patient (id int4 not null, diagnosis_id int4, patient_details_id int4, primary key (id));
create table doctor_ditales (id int4 not null, name varchar(255) not null, doctor_id int4, primary key (id));
create table epicrisis (id int4 not null, info varchar(255), appointment_id int4, primary key (id));
create table patient_details (id int4 not null, status varchar(255), patient_id int4, primary key (id));
create table users (id int4 not null, avatar_file_name varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, position varchar(255), credential_id int4, primary key (id));
alter table credentials add constraint UK_6pka8top3ggqmjvppakv4ygl8 unique (email);
alter table appointment_users add constraint FKeh0m7rwra7titkplngcm26ovl foreign key (appointment_id) references appointment;
alter table appointment_users add constraint FKbp0sfj9ne67frff7anb4qvcgu foreign key (doctor_id) references users;
alter table appointment_users add constraint FK4hdyxj4xkndvcrihfasask5qh foreign key (patient_id) references users;
alter table diagnosis_patient add constraint FK1b85dla6ti6o6sls7niljq8id foreign key (diagnosis_id) references diagnosis;
alter table diagnosis_patient add constraint FK8am06v0qfy4wgiomq8f93ixbv foreign key (patient_details_id) references patient_details;
alter table doctor_ditales add constraint FK8otoqm5qsn5dlgmc5qg0qrikv foreign key (doctor_id) references users;
alter table epicrisis add constraint FKrcx0fkxjpxm4hib7uhw72x2dd foreign key (appointment_id) references appointment;
alter table patient_details add constraint FK2hgl27xxfpx1u4ja3xx9o0n6h foreign key (patient_id) references users;
alter table users add constraint FK45ch6clbute36imxcljd6njiw foreign key (credential_id) references credentials;

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