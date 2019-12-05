/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Interface;

import App.Manager.Model.Tbl_Subject;
import java.util.List;

/**
 *
 * @author PC Hoang Anh
 */
public interface I_Subject {
    public void insert(Tbl_Subject s);
    public List<Tbl_Subject> select();
    public void update(Tbl_Subject s);
    public void delete(int id);
}
