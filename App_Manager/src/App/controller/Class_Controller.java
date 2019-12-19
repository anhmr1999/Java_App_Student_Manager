/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.controller;

import App.model.tbl_Class;
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
public class Class_Controller {
    Connection cnn;

    public Class_Controller(Connection cnn) {
        this.cnn = cnn;
    }
    
    public int insert(tbl_Class c){
        int row = 0;
        try {
            String sql = "insert into tbl_Class(Name,Course_ID,Teacher_ID) values(?,?,?)";
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, c.getName());
            PS.setInt(2, c.getCourse_id());
            PS.setInt(3, c.getTeacher_id());
            row = PS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Class_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public List<tbl_Class> select(String check){
        List<tbl_Class> LC = new ArrayList<>();
        String sql = "select * from tbl_Class "+check;
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            ResultSet rs =PS.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int course_id = rs.getInt("Course_ID");
                int teach_id = rs.getInt("Teacher_ID");
                LC.add(new tbl_Class(id, name, course_id, teach_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Class_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LC;
    }
    
    public boolean update(tbl_Class c){
        boolean check = false;
        String sql = "update tbl_Class set Name = ?, Course_ID = ?, Teacher_ID = ? where ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, c.getName());
            PS.setInt(2, c.getCourse_id());
            PS.setInt(3, c.getTeacher_id());
            PS.setInt(4, c.getId());
            PS.execute();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(Class_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    public boolean delete(int id){
        boolean check = false;
        String sql = "delete from tbl_Class where ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setInt(1, id);
            PS.execute();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(Class_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
