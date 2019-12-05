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
public class Tbl_Mark {

    private int Student_ID;
    private int Subject_ID;
    private float Mark;
    private int Exam_Time;
    private int status;
    private String note;

    //Contructor dùng để lấy và sửa dữ liệu
    public Tbl_Mark(int Student_ID, int Subject_ID, float Mark, int Exam_Time, int status, String note) {
        this.Student_ID = Student_ID;
        this.Subject_ID = Subject_ID;
        this.Mark = Mark;
        this.Exam_Time = Exam_Time;
        this.status = status;
        this.note = note;
    }

    //Contructor dùng để thêm dữ liệu
    public Tbl_Mark(int Subject_ID, float Mark, int Exam_Time, int status, String note) {
        this.Subject_ID = Subject_ID;
        this.Mark = Mark;
        this.Exam_Time = Exam_Time;
        this.status = status;
        this.note = note;
    }

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
        return Mark;
    }

    public void setMark(float Mark) {
        this.Mark = Mark;
    }

    public int getExam_Time() {
        return Exam_Time;
    }

    public void setExam_Time(int Exam_Time) {
        this.Exam_Time = Exam_Time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Tbl_Mark{" + "Student_ID=" + Student_ID + ", Subject_ID=" + Subject_ID + ", Mark=" + Mark + ", Exam_Time=" + Exam_Time + ", status=" + status + ", note=" + note + '}';
    }

}
