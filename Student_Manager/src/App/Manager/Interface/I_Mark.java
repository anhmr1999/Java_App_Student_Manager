/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Interface;

import App.Manager.Model.Tbl_Mark;
import java.util.List;

/**
 *
 * @author PC Hoang Anh
 */
public interface I_Mark {
    public void insert(Tbl_Mark m);
    public List<Tbl_Mark> select();
    public List<Tbl_Mark> select_all();
    public void update(Tbl_Mark m);
    public void delete(int id_student, int id_subject);
}
