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
public class tbl_Subject {

    private int ID;
    private String name;

    public tbl_Subject(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public tbl_Subject(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "tbl_Subject{" + "ID=" + ID + ", name=" + name + '}';
    }

}
