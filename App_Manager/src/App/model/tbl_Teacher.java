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
public class tbl_Teacher {

    private int ID;
    private String name;
    private String phone;
    private String email;
    private String pass;
    private String address;
    private String DOB;
    private int status;
    private int role_ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole_ID() {
        return role_ID;
    }

    public void setRole_ID(int role_ID) {
        this.role_ID = role_ID;
    }

    public tbl_Teacher(int ID, String name, String phone, String email, String pass, String address, String DOB, int status, int role_ID) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.DOB = DOB;
        this.status = status;
        this.role_ID = role_ID;
    }

    public tbl_Teacher(String name, String phone, String email, String pass, String address, String DOB, int status, int role_ID) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.DOB = DOB;
        this.status = status;
        this.role_ID = role_ID;
    }

    @Override
    public String toString() {
        return "tbl_Teacher{" + "ID=" + ID + ", name=" + name + ", phone=" + phone + ", email=" + email + ", pass=" + pass + ", address=" + address + ", DOB=" + DOB + ", status=" + status + ", role_ID=" + role_ID + '}';
    }

}
