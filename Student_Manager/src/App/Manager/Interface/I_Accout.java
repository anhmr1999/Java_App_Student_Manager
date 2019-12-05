/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Manager.Interface;

import App.Manager.Model.Tbl_Accout;
import java.util.List;

/**
 *
 * @author PC Hoang Anh
 */
public interface I_Accout {
    public void insert(Tbl_Accout accout);
    public List<Tbl_Accout> select();
    public void update(Tbl_Accout accout);
    public void delete(int id);
}
