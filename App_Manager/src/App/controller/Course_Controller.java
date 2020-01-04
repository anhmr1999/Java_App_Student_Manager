/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.controller;

import App.model.tbl_Course;
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
public class Course_Controller {
    Connection cnn;

    public Course_Controller(Connection cnn) {
        this.cnn = cnn;
    }
    
    public int insert(tbl_Course c){
        int row = 0;
        String sql = "insert into tbl_Course(Name,Begin_date,End_date) values(?,?,?)";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, c.getName());
            PS.setString(2, c.getBegin_date());
            PS.setString(3, c.getEnd_date());
            row = PS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public List<tbl_Course> select(String check){
        List<tbl_Course> LC = new ArrayList<>();
        String sql = "select * from tbl_Course "+check;
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String begin_date = rs.getString("Begin_date");
                String end_date = rs.getString("End_date");
                LC.add(new tbl_Course(id, name, begin_date, end_date));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LC;
    }
    
    public boolean update(tbl_Course c){
        boolean check = false;
        String sql = "update tbl_Course set Name = ?, Begin_date = ?, End_date = ? where ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, c.getName());
            PS.setString(2, c.getBegin_date());
            PS.setString(3, c.getEnd_date());
            PS.setInt(4, c.getId());
            PS.execute();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(Course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    public boolean delete(int id){
        boolean check = false;
        String sql = "delete from tbl_Course where ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setInt(1, id);
            PS.execute();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(Course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
