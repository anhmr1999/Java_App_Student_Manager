/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC Hoang Anh
 */
public class Connect {
    // Serve trên máy Hoàng Anh
    private final String url = "jdbc:sqlserver://localhost\\DESKTOP-87H3MR8\\MY_DB:1433;databaseName=Student_Manager";
    private final String user = "sa";
    private final String pass = "12345";
    
    public Connection ConnectDB(){
        Connection Cnn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Cnn = DriverManager.getConnection(url, user, pass);
            System.out.println("ĐÃ KẾT NỐI THÀNH CÔNG");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return Cnn;
    } 
}
