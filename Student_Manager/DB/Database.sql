/* drop database Student_Manager */

create database Student_Manager
go

use Student_Manager
go

create table tbl_Class(
	ID int Identity primary key,
	Name nvarchar(100) not null
)
GO

create table tbl_Course( /* Khóa học */
	ID int Identity primary key,
	Name nvarchar(100) not null,
	AllTime int not null /* Thời gian học, tính bằng tháng */
)
GO

create table tbl_Accout( /* Giảng viên - Quản trị */
	ID int Identity primary key,
	Name nvarchar(100) not null,
	Email varchar(200) unique not null,
	Login_pass varchar(150) not null,
	_Address nvarchar(250),
	Phone varchar(11) unique not null,
	Lever tinyint not null, /* 10 - Tài khoản cấp cao nhất, còn lại là giáo viên bình thường, 0 Tài khoản vô danh tính - Đăng ký các tài khoản */
	DOB date default('1990-01-01'),
)
GO

create table tbl_Student(
	ID int Identity primary key,
	RollNo nvarchar(5) unique not null,
	Name nvarchar(100) not null,
	_Address nvarchar(250) not null,
	Phone nvarchar(11) unique not null,
	Email nvarchar(100) unique not null,
	DOB date default('2000-01-01'),
	_Status tinyint not null, /* 1 - đang học, 2 - đang bảo lưu, 3 - đã học xong, 4  */
	BeginDate date not null, /* Ngày nhập học */
	_Type tinyint not null, /* Hạnh kiểm: 1 - Tốt, 2 - Khá, 3 -  Trung bình, 4 - kém, 5 - chưa xét hạnh kiểm*/
	Class_ID int foreign key references tbl_Class(ID),
	Course_ID int foreign key references tbl_Course(ID)
)
GO

create table tbl_Subject( /* Môn học */
	ID int identity primary key,
	Name nvarchar(100) not null,
	Teacher_ID int foreign key references tbl_Accout(ID)
)
GO

create table tbl_Mark( /* Bảng điểm */
	Student_ID int foreign key references tbl_Student(ID),
	Subject_ID int foreign key references tbl_Subject(ID),
	Mark float default(0),
	Exam_Times tinyint default(1), /* Số lần thi */
	_Status tinyint default(0), /* 0 - Bình thường, 1 - Đã cấm thi, 2 - Đang/chờ thi lại, 4 - Học lại môn */
	Note nvarchar(500) default('Không có nhận xét')
)
GO

create table tbl_History( /* Lịch sử thao tác với database */
	ID int identity primary key,
	_Type nvarchar(100) not null,
	Acc_ID int foreign key references tbl_Accout(ID),
	Tbl_Edit nvarchar(100) not null,
	Time_Edit date default(GETDATE())
)
GO


/* Từ đây bắt đầu bôi đen từng đoạn rồi chạy nha */
/* 1 THÊM LỚP MỚI */
create proc new_class @name nvarchar(100), @acc_ID int
AS
BEGIN
	INSERT INTO tbl_Class(Name) VALUES(@name)
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Tạo lớp mới',@acc_ID,N'Bảng lớp học')
END
/* 1- END */

/* 2 SỬA THÔNG TIN LỚP */
create proc edit_class @name nvarchar(100), @id int, @acc_ID int
AS
BEGIN
	update tbl_Class SET Name = @name WHERE ID = @id
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Sửa tên lớp',@acc_ID,N'Bảng lớp học')
END
/* 2- END */

/* 3 TẠO TÀI KHOẢN MỚI */
create proc new_acc @name nvarchar(100), @mail nvarchar(200), @pass nvarchar(150), @addres nvarchar(250), @phone nvarchar(11), @lever tinyint, @DOB date
AS
BEGIN
	INSERT INTO tbl_Accout(Name,Email,Login_pass,_Address,Phone,Lever,DOB) VALUES (@name,@mail,@pass,@addres,@phone,@lever,@DOB)
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Thêm tài khoản mới ',1,N'Bảng Tài khoản - Giáo viên')
END
/* 3- END */

/* 4 SỬA THÔNG TIN TÀI KHOẢN*/
create proc edit_acc @id int, @name nvarchar(100), @mail nvarchar(200), @pass nvarchar(150), @phone nvarchar(11), @lever tinyint, @DOB date
AS
BEGIN
	update tbl_Accout SET Name = @name, Email = @mail, Login_pass = @pass, Phone = @phone, Lever = @lever, DOB = @DOB WHERE ID = @id
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Sửa tài khoản ',@id,N'Bảng Tài khoản - Giáo viên')
END
/* 4-END */

/* 5 XÓA TÀI KHOẢN*/
create proc delete_acc @id int
AS
BEGIN
	delete from tbl_Accout WHERE ID = @id
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Sửa tài khoản ',@id,N'Bảng Tài khoản - Giáo viên')
END
/* 5- END*/

/* 6 TẠO MÔN KHÓA HỌC MỚI */
create proc new_course @name nvarchar(100),@alltime int, @acc_ID int
AS
BEGIN
	INSERT INTO tbl_Course(Name,AllTime) VALUES(@name,@alltime)
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Tạo lớp mới',@acc_ID,N'Bảng lớp học')
END
/* 6- END */

/* 7 SỬA THÔNG TIN KHÓA HỌC */
create proc edit_course @name nvarchar(100), @id int, @alltime int, @acc_ID int
AS
BEGIN
	update tbl_Course SET Name = @name WHERE ID = @id
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Sửa tên lớp',@acc_ID,N'Bảng lớp học')
END
/* 7-END */

/* 8 THÊM SINH VIÊN MỚI */
create proc add_student @roll nvarchar(5), @name nvarchar(100), @addres nvarchar(250), @phone nvarchar(11), @mail nvarchar(100), @DOB date, @statuss tinyint, @BeginDate date, @types tinyint, @Cl_ID int, @Co_ID int, @acc_ID int
AS
BEGIN
	INSERT INTO tbl_Student(RollNo,Name,_Address,Phone,Email,DOB,_Status,BeginDate,_Type,Class_ID,Course_ID) VALUES (@roll,@name,@addres,@phone,@mail,@DOB,@statuss,@BeginDate,@types,@Cl_ID,@Co_ID)
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Thêm học sinh mới',@acc_ID,N'Bảng học sinh')
END
/* 8- END */

/* 9 SỬA THÔNG TIN SINH VIÊN */
create proc edit_student @id int, @roll nvarchar(5), @name nvarchar(100), @addres nvarchar(250), @phone nvarchar(11), @mail nvarchar(100), @DOB date, @statuss tinyint, @BeginDate date, @types tinyint, @Cl_ID int, @Co_ID int, @acc_ID int
AS
BEGIN
	UPDATE tbl_Student SET RollNo = @roll, Name= @name, _Address= @addres, Phone= @phone, Email= @mail, DOB= @DOB, _Status = @statuss, BeginDate = @BeginDate, _Type = @types, Class_ID = @Cl_ID, Course_ID = @Co_ID WHERE ID = @id
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Sửa thông tin học sinh',@acc_ID,N'Bảng học sinh')
END
/* 9-END */

/* 10 THÊM MÔN HỌC MỚI */
create proc add_subject @name nvarchar(100), @id int, @acc_ID int
AS 
BEGIN
	INSERT INTO tbl_Subject(Name,Teacher_ID) VALUES (@name, @id)
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Thêm môn học',@acc_ID,N'Bảng môn học')
END
/* 10- END */

/* 11 SỬA THÔNG TIN MÔN HỌC */
create proc edit_subject @id int, @name nvarchar(100), @Teacher int, @acc_ID int
AS 
BEGIN
	update tbl_Subject SET Name = @name, Teacher_ID = @Teacher WHERE ID = @id
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Sửa môn học',@acc_ID,N'Bảng môn học')
END
/* 11- END */

/* 12 THÊM ĐIỂM CHO HỌC SINH SAU KÌ THI */
create proc add_mark @sub int, @student int, @mark float, @exam tinyint, @statuss tinyint, @note nvarchar(500), @acc_ID int
AS
BEGIN
	INSERT INTO tbl_Mark(Subject_ID,Student_ID,Mark,Exam_Times,_Status,Note) VALUES (@sub, @student, @mark, @exam, @statuss, @note)
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Thêm điểm cho sinh viên',@acc_ID,N'Bảng điểm')
END 
/* 12- END */

/* 13 SỬA ĐIỂM CHO HỌC SINH SAU THI LẠI, HỌC LẠI,...*/
create proc edit_mark @sub int, @student int, @mark float, @exam tinyint, @statuss tinyint, @note nvarchar(500), @acc_ID int
AS
BEGIN
	update tbl_Mark SET Mark = @mark, Exam_Times = @exam, _Status = @statuss, Note = @note WHERE Subject_ID = @sub AND Student_ID = @student
	INSERT INTO tbl_History(_Type,Acc_ID,Tbl_Edit) VALUES(N'Thêm điểm cho sinh viên',@acc_ID,N'Bảng điểm')
END 
/* 13-END */