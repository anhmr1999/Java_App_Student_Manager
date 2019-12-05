/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Controller;

import App.Manager.Model.Tbl_History;
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
public class History_Controller {
    Connection cnn;

    public History_Controller(Connection cnn) {
        this.cnn = cnn;
    }
    
    public List<Tbl_History> select(){
        List<Tbl_History> LH = new ArrayList<>();
        String sql = "Select * FROM tbl_History";
        try {
            CallableStatement cs = cnn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {                
                int ID = rs.getInt("ID");
                String type = rs.getString("_Type");
                int ID_acc = rs.getInt("Acc_ID");
                String tbl = rs.getString("Tbl_Edit");
                String edit_date = rs.getString("Time_Edit");
                LH.add(new Tbl_History(ID, type, ID_acc, tbl, edit_date));
            }
        } catch (SQLException ex) {
            Logger.getLogger(History_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LH;
    }
}
