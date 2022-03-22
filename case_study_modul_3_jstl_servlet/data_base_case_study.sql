drop database if exists furama_case_study;
create database furama_case_study;
use furama_case_study;
SET SQL_SAFE_UPDATES = 0;

create table position (
position_id int auto_increment primary key,
position_name varchar(45)
);

insert into position values
(1,'GM'),(2,'Manager'),(3,'Supervisor'),(4,'Employee'), (5,'Sever'),(6, 'Receptionist');


create table education_degree (
degree_id int auto_increment primary key,
degree_name varchar(45)
);


insert into education_degree  values
(1,'DH'),(2,'CD'),(3,'TC');


create table division(
division_id int auto_increment primary key,
division_name varchar(45)
);

insert into division values 
(1,'operation'),(2,'kitchen'),(3,'restaurant');

create table customer_type(
customer_type_id int auto_increment primary key,
customer_type_name varchar(45) 
);

insert into customer_type values
(1,'Diamond'),(2,'Platium'),(3,'Gold'),(4,'Sliver'),(5,'Member');

create table rental_type(
rental_type_id int auto_increment primary key,
rental_type_name varchar(45),
price int
);

insert into rental_type values (1,'Day',1000),(2,'Week',5000),(3,'Month',1500);

create table service_type (
service_type_id int auto_increment primary key,
service_type_name varchar(45)
);

insert into service_type(service_type_id, service_type_name) values 
(1,'Villa'),(2,'House'),(3,'Room');

create table employee(
employee_id int auto_increment primary key,
employee_name varchar(45),
position_id int,
degree_id int,
division_id int,
birthday date,
id_card varchar(45),
salary varchar(45),
phone varchar(45),
email varchar(45),
address varchar(45),
foreign key(position_id) references position (position_id),
foreign key(degree_id) references education_degree(degree_id),
foreign key(division_id) references division(division_id)
);

insert into employee (employee_id, employee_name, position_id, 
degree_id, division_id, birthday, id_card, salary, phone, email, address) values
(1001,'Truong Hung',1,2,3,'1989-09-09', 'A123456', 10, 0905112112, 'TruongHung@gmail.com', 'Hải Châu'),
(1002,'Nguyen Thao',2,3,3,'1990-09-08', 'A123457', 20, 0863159875, 'NguyenThao@gmail.com', 'Liên Chiểu'),
(1003,'Hoang Khoi',3,3,2,'1989-08-08', 'A123458', 30, 0121589789, 'HoangKhoi@gmail.com', 'Hải Châu'),
(1004,'Nguyen Hung',3,2,3,'1986-08-08', 'A123459', 30, 0987154698, 'NguyenHung@gmail.com', 'Hòa phú'),
(1005,'Nguyen Hoang',3,2,3,'1988-01-01', 'A123489', 30, 0121458987, 'NguyenHoang@gmail.com', 'Hòa Vang');

create table customer(
customer_id int auto_increment primary key,
customer_type_id int,
customer_name varchar(45),
birthday date,
gender varchar(10),
id_card varchar(45),
phone varchar(20),
email varchar(45),
address varchar(45),
foreign key(customer_type_id) references customer_type(customer_type_id)
);


insert into customer(customer_id, customer_type_id, customer_name, birthday, address) values
(101,5,'Nguyen Anh', '1981-10-20', 'Da Nang'),
(102,1,'Nguyen Binh', '1988-11-20', 'Quang Ngai'),
(103,2,'Nguyen An', '1963-10-20', 'Quang Nam'),
(104,1,'Nguyen Trai', '1982-09-20', 'Quang Ngai'),
(105,4,'Doan Truong', '1983-11-20', 'Hue'),
(106,2,'Nguyen Anh', '1981-10-20', 'Da Nang'),
(107,5,'Dinh Thang', '1985-06-20', 'Ho Chi Minh');

create table service(
service_id int primary key auto_increment,
service_name varchar(45),
area int,
cost int,
number_of_person int,
rental_type_id int,
service_type_id int,
standard_room varchar(45),
description_other_convenience varchar(45),
pool_area int,
number_of_floors int,
foreign key (rental_type_id) references rental_type(rental_type_id),
foreign key (service_type_id) references service_type(service_type_id)
);


insert service values 
(101,'Villa_A',50,300,2,1,3,null,null,null,null),
(102,'Villa_B',60,400,4,1,3,null,null,null,null),
(103,'House_A',30,200,2,1,2,null,null,null,null),
(104,'House_B',40,250,2,2,2,null,null,null,null),
(105,'Room_A',20, 100,1,1,1,null,null,null,null),
(106,'Room_B',20, 100,2,1,1,null,null,null,null),
(107,'Room_C',20, 100,1,2,1,null,null,null,null);

SET FOREIGN_KEY_CHECKS=0;
create table contract (
contract_id int primary key auto_increment,
employee_id int,
customer_id int,
service_id int,
check_in date,
check_out date,
deposit int,
total_money int,
foreign key (employee_id) references employee(employee_id) on delete set null,
foreign key (customer_id)  references customer(customer_id) on delete set null,
foreign key (service_id) references service(service_id) on delete set null
);


insert into contract (contract_id, employee_id, customer_id, service_id, check_in, check_out, deposit, total_money) values 
	   (1,1001,101,1,'2015-08-11','2016-12-16',20,12000),
       (2,1002,101,2,'2018-07-21','2018-10-10',30,300),
	   (3,1003,103,3,'2021-05-01','2021-08-12',5,50),
	   (4,1004,107,3,'2019-12-12','2019-12-19',10,1000),
	   (5,1003,107,2,'2019-10-10','2019-10-11',50,5000),
	   (6,1003,107,3,'2019-12-12','2019-12-15',50,11000);

create table attach_service(
a_id int auto_increment primary key,
a_name varchar(45),
a_price int,
unit int,
`status` bit default 0
);

insert into attach_service (a_id, a_name, a_price, unit, `status`) values
(1,'Freepool',50,3,1),(2,'Free Breakfast',20,5,1);

create table detail_contract(
detail_contract_id int auto_increment primary key,
contract_id int,
a_id int,
quantity int,
-- unique(contract_id, a_id),
foreign key(contract_id) references contract(contract_id) on delete set null,
foreign key(a_id) references attach_service(a_id) on delete set null
);

insert into detail_contract(detail_contract_id, contract_id, a_id, quantity)
values (1,1,2,3),
       (2,2,1,4),
       (3,3,2,9),
       (4,5,1,25),
       (5,4,2,25),
       (6,6,2,5)
       ;
       
create table `login` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `login` (`username`, `password`) VALUES 
		("TruongHung", "TruongHung"),
        ("NguyenThao", "NguyenThao"),
        ("HoangKhoi", "HoangKhoi"),
        ("NguyenHung", "NguyenHung"),
        ("NguyenHoang", "NguyenHoang");



