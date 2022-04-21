show databases;
create database EmpPayRollService;
use EmpPayRollService;
select database();
create table employee_payroll(
id int unsigned not null auto_increment,
name varchar(50) not null,
salary double not null,
start Date not null,
primary key (id));
desc employee_payroll;
insert into employee_payroll(name, salary, start)values
('Bill',1000000.0,'2018-01-03'),
('Terisa',2000000.0,'2019-11-13'),
('Charlie',3000000.0,'2020-05-21');
select * from employee_payroll;
select * from employee_payroll where start between CAST('2018-01-01' as date) and date(now());
select * from employee_payroll where start between CAST('2019-11-12' as date) and date(now());
alter table employee_payroll add gender char(1) after name;
desc employee_payroll;
update employee_payroll set gender = 'F' where name = 'Terisa';
select * from employee_payroll;
update employee_payroll set gender = 'M' where name = 'Bill' or name ='Charlie';
select sum(salary) from employee_payroll where gender='M' group by gender;
select sum(salary) from employee_payroll where gender='F' group by gender;
select avg(salary) from employee_payroll where gender='M' group by gender;
select avg(salary) from employee_payroll group by gender;
select gender,avg(salary) from employee_payroll group by gender;
select avg(salary) from employee_payroll;
select gender,count(name) from employee_payroll group by gender;
select * from employee_payroll;
alter table employee_payroll add phone_number varchar(100) after name;
alter table employee_payroll add address varchar(250) after phone_number;
alter table employee_payroll add department varchar(150) not null after address;
alter table employee_payroll alter address set default 'TBD';
select * from employee_payroll;
alter table employee_payroll rename column salary to basic_pay;
alter table employee_payroll add deductions Double not null after basic_pay;
alter table employee_payroll add taxable_pay Double not null after deductions;
alter table employee_payroll add tax Double not null after taxable_pay;
alter table employee_payroll add net_pay double not null after tax;
desc employee_payroll;
update employee_payroll set department='Sales' where name='Terisa';
insert into employee_payroll(name,department,gender,basic_pay,deductions,taxable_pay,tax,net_pay,start) values
('Terisa','Marketing','F',3000000.00,1000000.00,2000000.00,500000.00,1500000.00,'2018-01-03');
select * from employee_payroll;
create table company(
cmp_id int not null,
cmp_name varchar(100) not null);
show tables;
insert into company values(1,'Google');
select * from company;
insert into company values(2,'Amazon');
insert into company values(3,'Flipkart');
create table payroll(
	payment_id int NOT NULL,
	basic_pay double not null,
	deductions double not null,
	taxable_pay double not null,
	tax double not null,
	net_pay double not null,
	PRIMARY KEY(payment_id));
alter table company add primary key(cmp_id);
desc company;
desc payroll;
insert into payroll values(11,500000.00,100000.00,50000.00,25000.00,400000);
insert into payroll values(22,600000.00,200000.00,45000.00,35000.00,500000);
insert into payroll values(33,300000.00,100000.00,13000.00,20000.00,200000);
select * from payroll;
create table employee(
	emp_id int not null,
    emp_name varchar(70) not null,
    phone_no varchar(50) not null,
    address varchar(150),
    gender char(1) not null,
    Primary key(emp_id),
    payment_id int,
    cmp_id int,
    foreign key(payment_id) references payroll(payment_id),
    foreign key(cmp_id) references company(cmp_id));
show tables;
describe employee;
 create table department(
	dept_id int not null,
    dept_name varchar(60) not null,
    primary key(dept_id));
describe department;
insert into employee values(101,'sam','929292992','Hyd','F',22,1);
select * from employee;
insert into employee values(102,'VK','929389289','Delhi','M',11,2);
insert into employee values(103,'ABD','7477472874','Bangalore','M',33,3);
select * from employee;
describe department;
insert into department values(1,'Department-A');
insert into department values(2,'Department-B');
insert into department values(3,'Department-C');
insert into department values(4,'Department-D');
select * from department;
create table emp_dept(
	emp_id int,
	dept_id int,
	foreign key(emp_id) references employee(emp_id),
	foreign key(dept_id) references department(dept_id));    
describe emp_dept;
insert into emp_dept values(101,1);
insert into emp_dept values(101,4);
insert into emp_dept values(102,2);
insert into emp_dept values(103,3);
select employee.gender,sum(payroll.basic_pay) from employee 
inner join payroll on employee.payment_id=payroll.payment_id group by gender;
