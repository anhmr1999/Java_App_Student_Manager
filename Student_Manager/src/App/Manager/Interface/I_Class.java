/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Interface;

import App.Manager.Model.Tbl_Class;
import java.util.List;

/**
 *
 * @author PC Hoang Anh
 */
public interface I_Class {
    public void insert(Tbl_Class c);
    public List<Tbl_Class> select();
    public void update(Tbl_Class c);
    public void delete(int id);
}
