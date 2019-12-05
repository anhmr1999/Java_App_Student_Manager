/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Controller;

import App.Manager.Interface.I_Student;
import App.Manager.Model.Tbl_Student;
import java.sql.CallableStatement;
import java.sql.Connection;
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
public class Student_Controller implements I_Student {

    int id;
    Connection cnn;

    public Student_Controller(int id, Connection cnn) {
        this.id = id;
        this.cnn = cnn;
    }

    @Override
    public void insert(Tbl_Student s) {
        String sql = "{call add_student(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setString(1, s.getRoll());
            cs.setString(2, s.getName());
            cs.setString(3, s.getAddress());
            cs.setString(4, s.getPhone());
            cs.setString(5, s.getEmail());
            cs.setString(6, s.getDOB());
            cs.setInt(7, s.getStatus());
            cs.setString(8, s.getBeginDate());
            cs.setInt(9, s.getType());
            cs.setInt(10, s.getClass_id());
            cs.setInt(11, s.getCourse_id());
            cs.setInt(12, id);
            cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Student_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Tbl_Student> select() {
        List<Tbl_Student> LS = new ArrayList<>();
        String sql = "select * from tbl_Student where ID in (select Student_ID from tbl_Mark where Subject_ID in (select ID from tbl_Subject where Teacher_ID = ?))";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {                
                int ID = rs.getInt("ID");
                String roll = rs.getString("RollNo");
                String Name = rs.getString("Name");
                String _Address = rs.getString("_Address");
                String Phone = rs.getString("Phone");
                String Email = rs.getString("Email");
                String DOB = rs.getString("DOB");
                int stt = rs.getInt("_Status");
                String BeginDate = rs.getString("BeginDate");
                int _Type = rs.getInt("_Type");
                int Class_ID = rs.getInt("Class_ID");
                int Course_ID = rs.getInt("Course_ID");
                LS.add(new Tbl_Student(ID, roll, Name, _Address, Phone, Email, DOB, stt, BeginDate, _Type, Class_ID, Course_ID));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LS;
    }

    @Override
    public void update(Tbl_Student s) {
        String sql = "{call edit_student(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setInt(1, s.getID());
            cs.setString(2, s.getRoll());
            cs.setString(3, s.getName());
            cs.setString(4, s.getAddress());
            cs.setString(5, s.getPhone());
            cs.setString(6, s.getEmail());
            cs.setString(7, s.getDOB());
            cs.setInt(8, s.getStatus());
            cs.setString(9, s.getBeginDate());
            cs.setInt(10, s.getType());
            cs.setInt(11, s.getClass_id());
            cs.setInt(12, s.getCourse_id());
            cs.setInt(13, id);
            cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Student_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tbl_Student> select_all() {
        List<Tbl_Student> LS = new ArrayList<>();
        String sql = "select * from tbl_Student";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {                
                int ID = rs.getInt("ID");
                String roll = rs.getString("RollNo");
                String Name = rs.getString("Name");
                String _Address = rs.getString("_Address");
                String Phone = rs.getString("Phone");
                String Email = rs.getString("Email");
                String DOB = rs.getString("DOB");
                int stt = rs.getInt("_Status");
                String BeginDate = rs.getString("BeginDate");
                int _Type = rs.getInt("_Type");
                int Class_ID = rs.getInt("Class_ID");
                int Course_ID = rs.getInt("Course_ID");
                LS.add(new Tbl_Student(ID, roll, Name, _Address, Phone, Email, DOB, stt, BeginDate, _Type, Class_ID, Course_ID));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LS;
    }

}
