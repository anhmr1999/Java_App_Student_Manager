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
public class tbl_Class {

    private int id;
    private String name;
    private int Course_id;
    private int Teacher_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse_id() {
        return Course_id;
    }

    public void setCourse_id(int Course_id) {
        this.Course_id = Course_id;
    }

    public int getTeacher_id() {
        return Teacher_id;
    }

    public void setTeacher_id(int Teacher_id) {
        this.Teacher_id = Teacher_id;
    }

    public tbl_Class(int id, String name, int Course_id, int Teacher_id) {
        this.id = id;
        this.name = name;
        this.Course_id = Course_id;
        this.Teacher_id = Teacher_id;
    }

    public tbl_Class(String name, int Course_id, int Teacher_id) {
        this.name = name;
        this.Course_id = Course_id;
        this.Teacher_id = Teacher_id;
    }

    @Override
    public String toString() {
        return "tbl_Class{" + "id=" + id + ", name=" + name + ", Course_id=" + Course_id + '}';
    }

}
