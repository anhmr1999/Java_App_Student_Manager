/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Controller;

import App.Manager.Interface.I_Course;
import App.Manager.Model.Tbl_Course;
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
public class Course_Controller implements I_Course {

    Connection cnn;
    int id; // ID người đã thêm sửa xóa

    public Course_Controller(Connection cnn, int id) {
        this.cnn = cnn;
        this.id = id;
    }

    @Override
    public void insert(Tbl_Course C) {
        try {
            String sql = "{call new_course(?,?,?)}";
            CallableStatement CS = cnn.prepareCall(sql);
            CS.setString(1, C.getName());
            CS.setInt(2, C.getAll_Time());
            CS.setInt(3, id);
            CS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Tbl_Course> select() {
        List<Tbl_Course> LC = new ArrayList<>();
        String sql = "SELECT * FROM tbl_Course";
        try {
            CallableStatement CS = cnn.prepareCall(sql);
            ResultSet rs = CS.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");
                int allTime = rs.getInt("AllTime");
                LC.add(new Tbl_Course(ID, name, allTime));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LC;
    }

    @Override
    public void update(Tbl_Course C) {
        try {
            String sql = "{call edit_course(?,?,?,?)}";
            CallableStatement CS = cnn.prepareCall(sql);
            CS.setString(1, C.getName());
            CS.setInt(2, C.getID());
            CS.setInt(3, C.getAll_Time());
            CS.setInt(4, id);
            CS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Course_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
