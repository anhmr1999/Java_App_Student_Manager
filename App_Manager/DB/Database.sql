/* drop database Student_Manager */

create database Student_Manager
go

use Student_Manager
go

create table tbl_Course(
	ID int identity primary key,
	Name nvarchar(100) not null,
	Begin_date date,
	End_date date
)
GO

create table tbl_Role(
	ID int identity primary key,
	Name nvarchar(100)
)
GO

create table tbl_Student(
	ID int identity primary key,
	RollNo nvarchar(100) not null unique,
	Name nvarchar(100) not null,
	Phone varchar(10) unique,
	Email varchar(100) unique,
	Address nvarchar(100),
	DOB date default('2000/01/01'),
	Status tinyint default(1), /* 1 - đang học, 2 - đang bảo lưu, 3 - đã thôi học, 4 - đã tốt nghiệp */
	Course int foreign key references tbl_Course(ID)
)
GO

create table tbl_Teacher(
	ID int identity primary key,
	Name nvarchar(100) not null,
	Phone nvarchar(10) unique,
	Email varchar(100) unique,
	Address nvarchar(100),
	Password varchar(100) not null,
	DOB date,
	Status tinyint default(1), /* 1- đang dạy học, 2- đã về hưu */
	Lever_role int foreign key references tbl_Role(ID)
)
GO

create table tbl_Subject(
	ID int identity primary key,
	Name nvarchar(100) not null,
	Teacher int foreign key references tbl_Teacher(ID)
)
GO

create table tbl_Class(
	ID int identity primary key,
	Name nvarchar(100) not null,
	Max_Student int default(50),
	Subject int foreign key references tbl_Subject(ID)
)
GO

create table Class_Student(
	Student_ID int foreign key references tbl_Student(ID),
	Class_ID int foreign key references tbl_Class(ID)
)
GO

create table tbl_Mark(
	Student_ID int foreign key references tbl_Student(ID),
	Subject_ID int foreign key references tbl_Subject(ID),
	Mark float,
	Exam_times int default(1),
	Status int default(1), /* 1 - chưa thi- ko hiển thị, 2 - đã thi, đang tính điểm, 3 - cấm thi, 4 - trượt, 5 - qua môn */
	Note nvarchar(500)
)
GO

create table Student_Subject(
	Student_ID int foreign key references tbl_Student(ID),
	Subject_ID int foreign key references tbl_Subject(ID)
)
GO

create table tbl_History(
	ID int identity primary key,
	Teacher int foreign key references tbl_Teacher(ID),
	Changer nvarchar(100),
	Time_change datetime default(GETDATE())
)
GO