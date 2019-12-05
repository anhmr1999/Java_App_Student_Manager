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
public class Tbl_Accout {

    private int ID;
    private String name;
    private String email;
    private String pass;
    private String address;
    private String phone;
    private int lever;
    private String DOB;

    // Contructor để lấy và sử dữ liệu trong DB
    public Tbl_Accout(int ID, String name, String email, String pass, String address, String phone, int lever, String DOB) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
        this.lever = lever;
        this.DOB = DOB;
    }

    // Contructor để thêm dữ liệu vào DB
    public Tbl_Accout(String name, String email, String pass, String address, String phone, int lever, String DOB) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
        this.lever = lever;
        this.DOB = DOB;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Tbl_Accout{" + "ID=" + ID + ", name=" + name + ", email=" + email + ", pass=" + pass + ", address=" + address + ", phone=" + phone + ", lever=" + lever + ", DOB=" + DOB + '}';
    }

}
