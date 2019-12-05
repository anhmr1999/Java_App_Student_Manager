/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Controller;

import App.Manager.Interface.I_Class;
import App.Manager.Model.Tbl_Class;
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
public class Class_Controller implements I_Class {

    int id;
    Connection cnn;

    public Class_Controller(int id, Connection cnn) {
        this.id = id;
        this.cnn = cnn;
    }

    @Override
    public void insert(Tbl_Class c) {
        String sql = "{call new_class(?,?)}";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setString(1, c.getName());
            cs.setInt(2, id);
            cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Class_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Tbl_Class> select() {
        List<Tbl_Class> LC = new ArrayList<>();
        String sql = "SELECT * FROM tbl_Class";
        try {
            CallableStatement CS = cnn.prepareCall(sql);
            ResultSet rs = CS.executeQuery();
            while (rs.next()) {                
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");
                LC.add(new Tbl_Class(ID, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Class_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LC;
    }

    @Override
    public void update(Tbl_Class c) {
        String sql = "{call new_class(?,?,?)}";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            cs.setString(1, c.getName());
            cs.setInt(2, c.getID());
            cs.setInt(3, id);
            cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Class_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
