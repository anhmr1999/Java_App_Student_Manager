/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.model;

/**
 *
 * @author PC Hoang Anh
 */
public class tbl_Student {

    private int id;
    private String roll;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String DOB;
    private int Status;
    private int Class_ID;
    private String img;
    private int gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getClass_ID() {
        return Class_ID;
    }

    public void setClass_ID(int Class_ID) {
        this.Class_ID = Class_ID;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public tbl_Student(int id, String roll, String name, String phone, String email, String address, String DOB, int Status, int Class_ID, String img, int gender) {
        this.id = id;
        this.roll = roll;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.DOB = DOB;
        this.Status = Status;
        this.Class_ID = Class_ID;
        this.img = img;
        this.gender = gender;
    }

    public tbl_Student(String roll, String name, String phone, String email, String address, String DOB, int Status, int Class_ID, String img, int gender) {
        this.roll = roll;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.DOB = DOB;
        this.Status = Status;
        this.Class_ID = Class_ID;
        this.img = img;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "tbl_Student{" + "id=" + id + ", roll=" + roll + ", name=" + name + ", phone=" + phone + ", email=" + email + ", address=" + address + ", DOB=" + DOB + ", Status=" + Status + ", Class_ID=" + Class_ID + ", img=" + img + '}';
    }

}
