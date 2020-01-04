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

create table tbl_Role(
	ID int identity primary key,
	Name nvarchar(100)
)
GO

create table tbl_Teacher(
	ID int identity primary key,
	Name nvarchar(100),
	Phone nvarchar(11) unique,
	Email nvarchar(100) unique,
	Password nvarchar(100),
	Address nvarchar(100),
	DOB date default('1980/01/01'),
	Status tinyint default(1), /* 1- đang giảng dạy, 2- đã về hưu */
	Role_ID int foreign key references tbl_Role(ID)
)
GO

create table tbl_Class(
	ID int identity primary key,
	Name nvarchar(100),
	Course_ID int foreign key references tbl_Course(ID),
	Teacher_ID int foreign key references tbl_Teacher(ID)
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
	Gender tinyint default(1), /* 1 là nam, 2 là nữ, 3 là chưa xác định */
	img nvarchar(100),
	Status tinyint default(1), /* 1 - đang học, 2 - đang bảo lưu, 3 - đã thôi học, 4 - đã tốt nghiệp */
	Class_ID int foreign key references tbl_Class(ID)
)
GO

create table tbl_Subject(
	ID int identity primary key,
	Name nvarchar(100),
	accredit int default(3),
	price float default(500),
	status int default(1) /* 1 - đang giảng dạy, 2 - đã ngừng giảng dạy */
)
GO

create table tbl_Mark(
	Student_ID int foreign key references tbl_Student(ID) not null,
	Sub_ID int foreign key references tbl_Subject(ID) not null,
	Mark float,
	Status int default(3), /*1 - Đã thi, 2- Cấm thi, 3- Chưa thi*/
	Note nvarchar(500),
	primary key (Student_ID,Sub_ID)
)
GO

insert into tbl_Role values
(N'Hiệu trưởng'),
(N'Phó hiệu trưởng'),
(N'Giảng viên'),
(N'Giảng viên thực tập')
GO

insert into tbl_Course(Name,Begin_date,End_date) values
(N'Khóa học 1','2016-09-05','2020-05-25'),
(N'Khóa học 2','2017-09-05','2021-05-25'),
(N'Khóa học 3','2018-09-05','2022-05-25'),
(N'Khóa học 4','2019-09-05','2023-05-25'),
(N'Khóa học 5','2020-09-05','2024-05-25')
GO

insert into tbl_Class(Name,Course_ID,Teacher_ID) values
(N'CA1_K1_DNO',1,2),
(N'CA2_K1_DNO',1,2),
(N'CA2_K1_DNO',1,2),
(N'CA2_K2_DNO',1,3),
(N'CA3_K1_DNO',1,3),
(N'CA3_K2_DNO',1,3),
(N'CA3_K3_DNO',1,3)
GO

insert into tbl_Student(RollNo,Name,Phone,Email,Address,DOB,Gender,img,Status,Class_ID) values
('S0001',N'Ngô Thị Anh','012332112','Le.NT@gmail.com',N'Phạm Văn Đồng, Bắc Từ Liêm, Hà Nội','2004-01-09',2,'',1,1),
('S0002',N'Ngô Thị Duyên','03309134','Duyen.NT@gmail.com',N'Lạc Long Quân, Bắc Từ Liêm, Hà Nội','2004-01-27',2,'',1,1),
('S0003',N'Lê Văn Hải','01804288','Hai.LV@gmail.com',N'Phạm Văn Đồng, Bắc Từ Liêm, Hà Nội','2004-01-28',1,'',1,1),
('S0004',N'Trịnh Văn Mạnh','06930886 ','Manh.TV@gmail.com',N'Âu Cơ, Bắc Từ Liêm, Hà Nội','2004-02-27',1,'',1,1),
('S0005',N'Đoạn Văn Trường','08169277','Truong.DV@gmail.com',N'Khuất Duy Tiến, Nam Từ Liêm, Hà Nội','2004-05-24',1,'',1,1),
('S0006',N'Đàm Vĩnh Quang','01463691','Quang.DV@gmail.com',N', Lê Trọng Tấn, Thanh Xuân, Hà Nội','2004-04-12',1,'',1,1),
('S0007',N'Hạ Như Quỳnh','04238335','Quynh.HN@gmail.com',N'Định Công, Hoàng Mai, Hà Nội','2004-09-11',2,'',1,1),
('S0008',N'Trần Bảo Ý','0785386','Bao_Y.TB@gmail.com',N'Nguyễn Trãi, Thanh Xuân, Hà Nội','2004-12-23',2,'',1,1),
('S0009',N'Hạ Liên Liên','0976049','Lien.HLL@gmail.com',N'Nhân Chính, Thanh Xuân, Hà Nội','2004-11-11',2,'',1,1),
('S0010',N'Hà Như Yên','0516649','Yen.HN@gmail.com',N'Trung Hòa, Thanh Xuân, Hà Nội','2004-03-09',2,'',1,1),
('S0011',N'Nguyễn Bảo Anh','019641421','Anh.NB@gmail.com',N'Phạm Văn Đồng, Bắc Từ Liêm, Hà Nội','2005-02-09',1,'',1,2),
('S0012',N'Ngô Thị Ánh','01002362','Anh.NT@gmail.com',N'Yên Hòa, Đống Đa, Hà Nội','2005-01-18',2,'',1,2),
('S0013',N'Hải Hạ Duyên','01349002','Duyen.H2@gmail.com',N'Kim Mã, Ngọc Khánh, Ba Đình, Hà Nội','2005-11-09',2,'',1,2),
('S0014',N'Cao Văn Bình','07368690','Binh.CVC@gmail.com',N'Quan Hòa Hà Nội, Hà Nội','2005-12-03',1,'',1,2),
('S0015',N'Lò Văn Đến','02520059','Den.LV@gmail.com',N'Dịch Vọng, Cầu Giấy, Hà Nội','2005-12-02',1,'',1,2),
('S0016',N'Đoạn Văn Hoàng','01751392','Hoang.DV2@gmail.com',N'Xuân Thủy, Cầu Giấy, Hà Nội','2005-05-01',1,'',1,2),
('S0017',N'Hải Lê Hoàng','00383426','Hoang.HL1@gmail.com',N'Xuân Phương, Nam Từ Liêm, Hà Nội','2005-07-09',1,'',1,2),
('S0018',N'Nguyễn Thị Duy Loan','04089172','Loan.NTD@gmail.com',N'Vân Canh, Từ Liêm, Hà Nội','2005-06-06',2,'',1,2),
('S0020',N'Ngô Cao Ánh','09034556','Anh.NCC@gmail.com',N'Lê Quang Đạo, Mễ Trì, Từ Liêm, Hà Nội','2005-09-01',2,'',1,2)
GO

insert into tbl_Subject values
(N'Kinh Tế Vĩ Mô'),
(N'Kinh tế phát triển'),
(N'Kinh tế môi trường'),
(N'Lịch sử các học thuyết kinh tế'),
(N'Toán cao cấp'),
(N'Triết học Mác Lênin'),
(N'Tư tưởng Hồ Chí Minh'),
(N'Quan hệ kinh tế quốc tế'),
(N'Nguyên lý thống kê kinh tế'),
(N'Giao nhận vận tải'),
(N'Tin học đại cương'),
(N'Tiếng Anh giao tiếp')
GO

select * from tbl_Teacher
select * from tbl_Course
select * from tbl_Class
select * from tbl_Subject
select * from tbl_Student
select * from tbl_Role
select * from tbl_Mark