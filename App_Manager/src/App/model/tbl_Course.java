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
public class tbl_Course {

    private int id;
    private String name;
    private String begin_date;
    private String end_date;

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

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public tbl_Course(int id, String name, String begin_date, String end_date) {
        this.id = id;
        this.name = name;
        this.begin_date = begin_date;
        this.end_date = end_date;
    }

    public tbl_Course(String name, String begin_date, String end_date) {
        this.name = name;
        this.begin_date = begin_date;
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "tbl_Course{" + "id=" + id + ", name=" + name + ", begin_date=" + begin_date + ", end_date=" + end_date + '}';
    }

}
