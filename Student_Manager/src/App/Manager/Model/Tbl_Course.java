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
public class Tbl_Course {

    private int ID;
    private String Name;
    private int All_Time; // Số tháng h

    // Contructor dùng để lấy và sửa dữ liệu từ DB
    public Tbl_Course(int ID, String Name, int All_Time) {
        this.ID = ID;
        this.Name = Name;
        this.All_Time = All_Time;
    }

    // Contructor dùng để thêm dữ liệu vào DB
    public Tbl_Course(String Name, int All_Time) {
        this.Name = Name;
        this.All_Time = All_Time;
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

    public int getAll_Time() {
        return All_Time;
    }

    public void setAll_Time(int All_Time) {
        this.All_Time = All_Time;
    }

    @Override
    public String toString() {
        return "Tbl_Course{" + "ID=" + ID + ", Name=" + Name + ", All_Time=" + All_Time + '}';
    }

}
