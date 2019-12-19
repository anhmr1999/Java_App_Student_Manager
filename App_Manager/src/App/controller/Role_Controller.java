/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.controller;

import App.model.tbl_Role;
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
public class Role_Controller {
    Connection cnn;

    public Role_Controller(Connection cnn) {
        this.cnn = cnn;
    }
    
    public List<tbl_Role> select(){
        List<tbl_Role> TR = new ArrayList<>();
        String sql = "select * from tbl_Role";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                TR.add(new tbl_Role(id, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Role_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TR;
    }
}
