/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.controller;

import App.model.tbl_Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC Hoang Anh
 */
public class Student_Controller {
    Connection cnn;

    public Student_Controller(Connection cnn) {
        this.cnn = cnn;
    }
    
    public int insert(tbl_Student s){
        int row = 0;
        String sql = "insert into tbl_Student(RollNo,Name,Phone,Email, Address,DOB,Gender,img,Status,Class_ID) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, s.getRoll());
            PS.setString(2, s.getName());
            PS.setString(3, s.getPhone());
            PS.setString(4, s.getEmail());
            PS.setString(5, s.getAddress());
            PS.setString(6, s.getDOB());
            PS.setInt(7, s.getGender());
            PS.setString(8, s.getImg());
            PS.setInt(9, s.getStatus());
            PS.setInt(10, s.getClass_ID());
            row = PS.executeUpdate();
        } catch (SQLException ex ) {
             Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public List<tbl_Student> select(String check){
        List<tbl_Student> LS = new ArrayList<>();
        String sql = "select * from tbl_Student " + check;
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt("ID");
                String roll = rs.getString("RollNo");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");
                String mail = rs.getString("Email");
                String address = rs.getString("Address");
                String dob = rs.getString("DOB");
                int gender = rs.getInt("Gender");
                String img = rs.getString("img");
                int status = rs.getInt("Status");
                int class_id = rs.getInt("Class_ID");
                LS.add(new tbl_Student(id, roll, name, phone, mail, address, dob, status, class_id, img, gender));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LS;
    }
    
    public boolean update(tbl_Student s){
        boolean check;
        String sql = "update tbl_Student set RollNo = ?, Name = ?, Phone = ?, Email = ?, "
                + "Address = ?, DOB = ?, Gender = ?, img = ?, Status = ?, Class_ID = ? where ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, s.getRoll());
            PS.setString(2, s.getName());
            PS.setString(3, s.getPhone());
            PS.setString(4, s.getEmail());
            PS.setString(5, s.getAddress());
            PS.setString(6, s.getDOB());
            PS.setInt(7, s.getGender());
            PS.setString(8, s.getImg());
            PS.setInt(9, s.getStatus());
            PS.setInt(10, s.getClass_ID());
            PS.setInt(11, s.getId());
            PS.execute();
            check = true;
        } catch (SQLException ex ) {
            check = false;
            Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
