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
public class Tbl_History {

    private int ID;
    private String Type;
    private int Acc_ID;
    private String Tbl_edit;
    private String Time_edit;

    // Contructor để lấy dữ liệu ra
    public Tbl_History(int ID, String Type, int Acc_ID, String Tbl_edit, String Time_edit) {
        this.ID = ID;
        this.Type = Type;
        this.Acc_ID = Acc_ID;
        this.Tbl_edit = Tbl_edit;
        this.Time_edit = Time_edit;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getAcc_ID() {
        return Acc_ID;
    }

    public void setAcc_ID(int Acc_ID) {
        this.Acc_ID = Acc_ID;
    }

    public String getTbl_edit() {
        return Tbl_edit;
    }

    public void setTbl_edit(String Tbl_edit) {
        this.Tbl_edit = Tbl_edit;
    }

    public String getTime_edit() {
        return Time_edit;
    }

    public void setTime_edit(String Time_edit) {
        this.Time_edit = Time_edit;
    }

    @Override
    public String toString() {
        return "Tbl_History{" + "ID=" + ID + ", Type=" + Type + ", Acc_ID=" + Acc_ID + ", Tbl_edit=" + Tbl_edit + ", Time_edit=" + Time_edit + '}';
    }

}
