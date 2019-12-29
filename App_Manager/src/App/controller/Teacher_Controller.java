/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.controller;

import App.model.tbl_Teacher;
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
public class Teacher_Controller {

    Connection cnn;

    public Teacher_Controller(Connection cnn) {
        this.cnn = cnn;
    }

    public int insert(tbl_Teacher t) {
        int row = 0;
//        System.out.println(t.toString());
        String sql = "insert into tbl_Teacher(Name,Phone,Email,Password,Address,DOB,Status,Role_ID) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, t.getName());
            PS.setString(2, t.getPhone());
            PS.setString(3, t.getEmail());
            PS.setString(4, t.getPass());
            PS.setString(5, t.getAddress());
            PS.setString(6, t.getDOB());
            PS.setInt(7, t.getStatus());
            PS.setInt(8, t.getRole_ID());
            row = PS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Teacher_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public List<tbl_Teacher> select(String check) {
        List<tbl_Teacher> LT = new ArrayList<>();
        String sql = "select * from tbl_Teacher " + check;
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String pass = rs.getString("Password");
                String address = rs.getString("Address");
                String dob = rs.getString("DOB");
                int status = rs.getInt("Status");
                int role_id = rs.getInt("Role_ID");
                LT.add(new tbl_Teacher(id, name, phone, email, pass, address, dob, status, role_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LT;
    }

    public tbl_Teacher login(String mail, String password) {
        tbl_Teacher acc = null;
        String sql = "select * from tbl_Teacher where Email = ? AND Password = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, mail);
            PS.setString(2, password);
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String pass = rs.getString("Password");
                String address = rs.getString("Address");
                String dob = rs.getString("DOB");
                int status = rs.getInt("Status");
                int role_id = rs.getInt("Role_ID");
                acc = new tbl_Teacher(id, name, phone, email, pass, address, dob, status, role_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    public boolean update(tbl_Teacher t) {
        boolean check = false;
        String sql = "update tbl_Teacher set Name = ?, Phone = ?, Email = ?, Password = ?, Address = ?, DOB = ?, Status = ?, Role_ID = ? where ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, t.getName());
            PS.setString(2, t.getPhone());
            PS.setString(3, t.getEmail());
            PS.setString(4, t.getPass());
            PS.setString(5, t.getAddress());
            PS.setString(6, t.getDOB());
            PS.setInt(7, t.getStatus());
            PS.setInt(8, t.getRole_ID());
            PS.setInt(9, t.getID());
            PS.execute();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(Teacher_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
