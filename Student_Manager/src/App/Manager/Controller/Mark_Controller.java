/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Controller;

import App.Manager.Interface.I_Mark;
import App.Manager.Model.Tbl_Mark;
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
public class Mark_Controller implements I_Mark {

    int id;
    Connection cnn;

    public Mark_Controller(int id, Connection cnn) {
        this.id = id;
        this.cnn = cnn;
    }

    @Override
    public void insert(Tbl_Mark m) {
        try {
            String sql = "{call add_mark(?,?,?,?,?,?,?)}";
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setInt(1, m.getSubject_ID());
            cs.setInt(2, m.getStudent_ID());
            cs.setFloat(3, m.getMark());
            cs.setInt(4, m.getExam_Time());
            cs.setInt(5, m.getStatus());
            cs.setString(6, m.getNote());
            cs.setInt(7, id);
            cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Mark_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Tbl_Mark> select() {
        List<Tbl_Mark> LM = new ArrayList<>();
        String sql = "select * from tbl_Mark where Subject_ID in (select ID from tbl_Subject where Teacher_ID = ?)";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                int ID_student = rs.getInt("Student_ID");
                int ID_subject = rs.getInt("Subject_ID");
                float mark = rs.getFloat("Mark");
                int exam_times = rs.getInt("Exam_Times");
                int _Status = rs.getInt("_Status");
                String note = rs.getString("Note");
                LM.add(new Tbl_Mark(ID_student, ID_subject, mark, exam_times, _Status, note));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mark_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LM;
    }

    @Override
    public List<Tbl_Mark> select_all() {
        List<Tbl_Mark> LM = new ArrayList<>();
        String sql = "select * from tbl_Mark";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                int ID_student = rs.getInt("Student_ID");
                int ID_subject = rs.getInt("Subject_ID");
                float mark = rs.getFloat("Mark");
                int exam_times = rs.getInt("Exam_Times");
                int _Status = rs.getInt("_Status");
                String note = rs.getString("Note");
                LM.add(new Tbl_Mark(ID_student, ID_subject, mark, exam_times, _Status, note));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mark_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LM;
    }

    @Override
    public void update(Tbl_Mark m) {
        try {
            String sql = "{call edit_mark(?,?,?,?,?,?,?)}";
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setInt(1, m.getSubject_ID());
            cs.setInt(2, m.getStudent_ID());
            cs.setFloat(3, m.getMark());
            cs.setInt(4, m.getExam_Time());
            cs.setInt(5, m.getStatus());
            cs.setString(6, m.getNote());
            cs.setInt(7, id);
            cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Mark_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id_student, int id_subject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
