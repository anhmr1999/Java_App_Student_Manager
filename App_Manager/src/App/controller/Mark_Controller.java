/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.controller;

import App.model.tbl_Mark;
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
public class Mark_Controller {

    Connection cnn;

    public Mark_Controller(Connection cnn) {
        this.cnn = cnn;
    }

    public int insert(tbl_Mark m) {
        int row = 0;
        String sql = "insert into tbl_Mark(Student_ID,Sub_ID,Mark,Note) values(?,?,?,?)";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setInt(1, m.getStudent_ID());
            PS.setInt(2, m.getSubject_ID());
            PS.setFloat(3, m.getMark());
            PS.setString(4, m.getNote());
            row = PS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Mark_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public List<tbl_Mark> select(int id) {
        List<tbl_Mark> LM = new ArrayList<>();
        String sql = "select Student_ID, Sub_ID, Mark, Note from tbl_Mark where Student_ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setInt(1, id);
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {
                int Student_ID = rs.getInt("Student_ID");
                int Subject_ID = rs.getInt("Sub_ID");
                float mark = rs.getFloat("Mark");
                String note = rs.getString("Note");
                LM.add(new tbl_Mark(Student_ID, Subject_ID, mark, note));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mark_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LM;
    }
    
    public boolean update(tbl_Mark m){
        boolean check = false;
        String sql = "update tbl_Mark set Mark = ?, Note = ? where Student_ID = ? AND Sub_ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setFloat(1, m.getMark());
            PS.setString(2, m.getNote());
            PS.setInt(3, m.getStudent_ID());
            PS.setInt(4, m.getSubject_ID());
            PS.execute();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(Mark_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    public boolean delete(int student_id, int sub_id){
        boolean check = false;
        String sql = "delete from tbl_Mark where Student_ID = ? AND Sub_ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setInt(1, student_id);
            PS.setInt(2, sub_id);
            PS.execute();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(Mark_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
