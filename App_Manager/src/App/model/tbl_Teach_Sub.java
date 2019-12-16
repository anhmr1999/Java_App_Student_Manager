/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.model;

/**
 *
 * @author PC Hoang Anh
 */
public class tbl_Teach_Sub {

    private int ID;
    private int Teacher_ID;
    private int Subject_ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTeacher_ID() {
        return Teacher_ID;
    }

    public void setTeacher_ID(int Teacher_ID) {
        this.Teacher_ID = Teacher_ID;
    }

    public int getSubject_ID() {
        return Subject_ID;
    }

    public void setSubject_ID(int Subject_ID) {
        this.Subject_ID = Subject_ID;
    }

    public tbl_Teach_Sub(int ID, int Teacher_ID, int Subject_ID) {
        this.ID = ID;
        this.Teacher_ID = Teacher_ID;
        this.Subject_ID = Subject_ID;
    }

    public tbl_Teach_Sub(int Teacher_ID, int Subject_ID) {
        this.Teacher_ID = Teacher_ID;
        this.Subject_ID = Subject_ID;
    }

    @Override
    public String toString() {
        return "tbl_Teach_Sub{" + "ID=" + ID + ", Teacher_ID=" + Teacher_ID + ", Subject_ID=" + Subject_ID + '}';
    }

}
