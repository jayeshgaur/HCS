use healthcaresystem;

create table hcs_user(user_id bigint PRIMARY KEY AUTO_INCREMENT,user_password varchar(250),user_name varchar(250),
user_contact_no bigint,user_role varchar(250) DEFAULT 'Customer',user_email varchar(250),user_age int(10),
user_gender varchar(150));

create table hcs_center(center_id bigint PRIMARY KEY AUTO_INCREMENT,center_name varchar(250),
center_contact_no bigint(20),center_address varchar(250));

create table hcs_test(test_id bigint(20) PRIMARY KEY AUTO_INCREMENT,test_name varchar(250),
center_id_fk bigint(20),FOREIGN KEY(center_id_fk) REFERENCES hcs_center(center_id));

 create table hcs_appointment(appointment_id bigint PRIMARY KEY AUTO_INCREMENT,center_id_fk bigint,
 FOREIGN KEY(center_id_fk) REFERENCES hcs_center(center_id),test_id_fk bigint,
 FOREIGN KEY(test_id_fk) REFERENCES hcs_test(test_id),user_id_fk bigint ,
 FOREIGN KEY(user_id_fk) REFERENCES hcs_user(user_id),appointment_status int(2),
 appointment_date_time timestamp);healthcaresystem