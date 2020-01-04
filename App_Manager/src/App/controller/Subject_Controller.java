/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.controller;

import App.model.tbl_Subject;
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
public class Subject_Controller {
    Connection cnn;

    public Subject_Controller(Connection cnn) {
        this.cnn = cnn;
    }
    
    public int insert(tbl_Subject s){
        int row = 0;
        String sql = "insert into tbl_Subject(Name,accredit,price) values(?,?,?)";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, s.getName());
            PS.setInt(2, s.getAccredit());
            PS.setFloat(3, s.getPrice());
            row = PS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public List<tbl_Subject> select(String check){
        List<tbl_Subject> LS = new ArrayList<>();
        String sql = "select * from tbl_Subject "+check;
        PreparedStatement PS;
        try {
            PS = cnn.prepareCall(sql);
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int accredit = rs.getInt("accredit");
                float price = rs.getFloat("price");
                int status = rs.getInt("status");
                LS.add(new tbl_Subject(id, name, accredit, price,status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LS;
    }
    
    public boolean update(tbl_Subject s){
        boolean check = false;
        String sql = "update tbl_Subject set Name = ?,accredit = ?, price = ?, status = ? where ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setString(1, s.getName());
            PS.setInt(2, s.getAccredit());
            PS.setFloat(3, s.getPrice());
            PS.setInt(4, s.getStatus());
            PS.setInt(5, s.getID());
            PS.execute();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    public boolean delete(int id){
        boolean check = false;
        String sql = "delete from tbl_Subject where ID = ?";
        try {
            PreparedStatement PS = cnn.prepareCall(sql);
            PS.setInt(1, id);
            PS.execute();
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(Subject_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
