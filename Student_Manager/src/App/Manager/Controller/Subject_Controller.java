/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Controller;

import App.Manager.Interface.I_Subject;
import App.Manager.Model.Tbl_Subject;
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
public class Subject_Controller implements I_Subject {

    int id;
    Connection cnn;

    public Subject_Controller(int id, Connection cnn) {
        this.id = id;
        this.cnn = cnn;
    }

    @Override
    public void insert(Tbl_Subject s) {
        try {
            String sql = "{call add_subject(?,?,?)}";
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setString(1, s.getName());
            cs.setInt(2, s.getTeacher_ID());
            cs.setInt(3, id);
            cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Tbl_Subject> select() {
        List<Tbl_Subject> LS = new ArrayList<>();
        String sql = "SELECT * FROM tbl_Subject Where Teacher_ID = ?";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {                
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");
                int teach = rs.getInt("Teacher_ID");
                LS.add(new Tbl_Subject(ID, name, teach));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LS;
    }

    @Override
    public void update(Tbl_Subject s) {
        try {
            String sql = "{call add_subject(?,?,?,?)}";
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setInt(1, s.getID());
            cs.setString(2, s.getName());
            cs.setInt(3, s.getTeacher_ID());
            cs.setInt(4, id);
            cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tbl_Subject> select_all() {
        List<Tbl_Subject> LS = new ArrayList<>();
        String sql = "SELECT * FROM tbl_Subject";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {                
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");
                int teach = rs.getInt("Teacher_ID");
                LS.add(new Tbl_Subject(ID, name, teach));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LS;
    }

}
