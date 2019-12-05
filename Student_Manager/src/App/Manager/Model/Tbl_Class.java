/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Model;

/**
 *
 * @author PC Hoang Anh
 */
public class Tbl_Class {

    private int ID;
    private String Name;

    // Contructor dùng để lấy và sửa dữ liệu trong DB
    public Tbl_Class(int ID, String Name) {
        this.ID = ID;
        this.Name = Name;
    }

    // Contructor dùng để thêm dữ liệu vào DB
    public Tbl_Class(String Name) {
        this.Name = Name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "Tbl_Class{" + "ID=" + ID + ", Name=" + Name + '}';
    }

}
