/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Interface;

import App.Manager.Model.Tbl_Student;
import java.util.List;

/**
 *
 * @author PC Hoang Anh
 */
public interface I_Student {
    public void insert(Tbl_Student s);
    public List<Tbl_Student> select();
    public void update(Tbl_Student s);
    public void delete(int id);
}
