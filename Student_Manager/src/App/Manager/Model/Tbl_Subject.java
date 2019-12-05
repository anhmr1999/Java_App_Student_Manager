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
public class Tbl_Subject {

    private int ID;
    private String name;
    private int Teacher_ID;

    // Contructor để lấy và sửa dữ liệu
    public Tbl_Subject(int ID, String name, int Teacher_ID) {
        this.ID = ID;
        this.name = name;
        this.Teacher_ID = Teacher_ID;
    }

    //Contructor để thêm dữ liệu
    public Tbl_Subject(String name, int Teacher_ID) {
        this.name = name;
        this.Teacher_ID = Teacher_ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacher_ID() {
        return Teacher_ID;
    }

    public void setTeacher_ID(int Teacher_ID) {
        this.Teacher_ID = Teacher_ID;
    }

    @Override
    public String toString() {
        return "Tbl_Subject{" + "ID=" + ID + ", name=" + name + ", Teacher_ID=" + Teacher_ID + '}';
    }

}
