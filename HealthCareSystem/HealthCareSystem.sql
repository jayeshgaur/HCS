create database HealthCareSystem;

use HealthCareSystem;

create table User(user_id bigint PRIMARY KEY AUTO_INCREMENT,user_name varchar(120), user_password varchar(200),user_contact_no bigint,user_role varchar(120),
user_email varchar(200),user_age int,user_gender varchar(10));
ALTER TABLE User AUTO_INCREMENT=100;

create table Center(center_id bigint PRIMARY KEY AUTO_INCREMENT,center_name varchar(500),center_address varchar(500),center_contact_no bigint);
ALTER TABLE Center AUTO_INCREMENT=100;
ALTER TABLE Center add column isEmpty int(2) DEFAULT 0;

create table Test(test_id bigint PRIMARY KEY AUTO_INCREMENT,test_name varchar(200), center_id bigint, FOREIGN KEY (center_id) REFERENCES Center(center_id));
ALTER TABLE Test AUTO_INCREMENT=100;
ALTER TABLE Test add column isEmpty int(2) DEFAULT 0;

create table Appointment(appointmenmt_id bigint PRIMARY KEY AUTO_INCREMENT,center_id bigint,FOREIGN KEY(center_id) REFERENCES Center(center_id),test_id bigint, FOREIGN KEY(test_id) REFERENCES Test(test_id),user_id bigint, FOREIGN KEY(user_id) REFERENCES User(user_id),appointment_status int(2),appointment_date_time datetime);
ALTER TABLE Appointment AUTO_INCREMENT=100;

