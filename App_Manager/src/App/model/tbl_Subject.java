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
    private int accredit;
    private float price;
    private int status;

    public tbl_Subject(int ID, String name, int accredit, float price, int status) {
        this.ID = ID;
        this.name = name;
        this.accredit = accredit;
        this.price = price;
        this.status = status;
    }

    public tbl_Subject(String name, int accredit, float price, int status) {
        this.name = name;
        this.accredit = accredit;
        this.price = price;
        this.status = status;
    }

    public int getAccredit() {
        return accredit;
    }

    public void setAccredit(int accredit) {
        this.accredit = accredit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "tbl_Subject{" + "ID=" + ID + ", name=" + name + ", accredit=" + accredit + ", price=" + price + ", status=" + status + '}';
    }

}
