/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Interface;

import App.Manager.Model.Tbl_Course;
import java.util.List;

/**
 *
 * @author PC Hoang Anh
 */
public interface I_Course {
    public void insert(Tbl_Course C);
    public List<Tbl_Course> select();
    public void update(Tbl_Course C);
    public void delete(int id);
}
