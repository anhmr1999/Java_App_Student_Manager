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
public class tbl_Mark {

    private int Student_ID;
    private int Subject_ID;
    private float mark;
    private int status;
    private String note;
    private String Ex_date;

    public int getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(int Student_ID) {
        this.Student_ID = Student_ID;
    }

    public int getSubject_ID() {
        return Subject_ID;
    }

    public void setSubject_ID(int Subject_ID) {
        this.Subject_ID = Subject_ID;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEx_date() {
        return Ex_date;
    }

    public void setEx_date(String Ex_date) {
        this.Ex_date = Ex_date;
    }

    public tbl_Mark(int Student_ID, int Subject_ID, float mark, int status, String note, String Ex_date) {
        this.Student_ID = Student_ID;
        this.Subject_ID = Subject_ID;
        this.mark = mark;
        this.status = status;
        this.note = note;
        this.Ex_date = Ex_date;
    }

    @Override
    public String toString() {
        return "tbl_Mark{" + "Student_ID=" + Student_ID + ", Subject_ID=" + Subject_ID + ", mark=" + mark + ", status=" + status + ", note=" + note + '}';
    }

}
