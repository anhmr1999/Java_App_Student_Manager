/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Model;

/**
 *
 * @author PC Hoang Anh
 */
public class Tbl_Student {

    private int ID;
    private String roll;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String DOB;
    private int status;
    private String BeginDate;
    private int type;
    private int class_id;
    private int course_id;

    // Contructor dùng để lấy và sửa dữ liệu trong DB
    public Tbl_Student(int ID, String roll, String name, String address, String phone, String email, String DOB, int status, String BeginDate, int type, int class_id, int course_id) {
        this.ID = ID;
        this.roll = roll;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.DOB = DOB;
        this.status = status;
        this.BeginDate = BeginDate;
        this.type = type;
        this.class_id = class_id;
        this.course_id = course_id;
    }

    // Contructor dùng để thêm dữ liệu
    public Tbl_Student(String roll, String name, String address, String phone, String email, String DOB, int status, String BeginDate, int type, int class_id, int course_id) {
        this.roll = roll;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.DOB = DOB;
        this.status = status;
        this.BeginDate = BeginDate;
        this.type = type;
        this.class_id = class_id;
        this.course_id = course_id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(String BeginDate) {
        this.BeginDate = BeginDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Tbl_Student{" + "ID=" + ID + ", roll=" + roll + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + ", DOB=" + DOB + ", status=" + status + ", BeginDate=" + BeginDate + ", type=" + type + ", class_id=" + class_id + ", course_id=" + course_id + '}';
    }

}
