/* drop database Student_Manager */

create database Student_Manager
go

use Student_Manager
go

create table tbl_Course(
	ID int identity primary key,
	Name nvarchar(100) not null,
	Begin_date date default(GETDATE()),
	End_date date
)
GO

create table tbl_Class(
	ID int identity primary key,
	Name nvarchar(100),
	Course_ID int foreign key references tbl_Course(ID)
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
	img nvarchar(100),
	Status tinyint default(1), /* 1 - đang học, 2 - đang bảo lưu, 3 - đã thôi học, 4 - đã tốt nghiệp */
	Class_ID int foreign key references tbl_Class(ID)
)
GO

create table tbl_Role(
	ID int identity primary key,
	Name nvarchar(100)
)
GO

create table tbl_Teacher(
	ID int identity primary key,
	Name nvarchar(100),
	Phone nvarchar(11),
	Email nvarchar(100),
	Password nvarchar(100),
	Address nvarchar(100),
	DOB date default('1980/01/01'),
	Status tinyint default(1), /* 1- đang giảng dạy, 2- đã về hưu */
	Role_ID int foreign key references tbl_Role(ID)
)
GO

create table tbl_Subject(
	ID int identity primary key,
	Name nvarchar(100)
)
GO

create table tbl_Teach_Sub(
	ID int identity primary key,
	Teacher_ID int foreign key references tbl_Teacher(ID),
	Subject_ID int foreign key references tbl_Subject(ID)
)
GO

create table tbl_Mark(
	Student_ID int foreign key references tbl_Student(ID),
	Sub_ID int foreign key references tbl_Teach_Sub(ID),
	Mark float default(0),
	Note nvarchar(500)
)
GO

insert into tbl_Role values
(N'Hiệu trưởng'),
(N'Phó hiệu trưởng'),
(N'Giảng viên'),
(N'Giảng viên thực tập')
GO
