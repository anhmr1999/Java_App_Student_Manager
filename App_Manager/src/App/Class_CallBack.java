/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import App.model.tbl_Teacher;

/**
 *
 * @author PC Hoang Anh
 */
public class Class_CallBack implements Login.Call_Login, MainFrame.Call_Logout {

    Login frame_login = new Login();

    public Class_CallBack() {
    }

    public void run_project() {
        frame_login.setCallBack(this);
        frame_login.setLocationRelativeTo(null);
        frame_login.setVisible(true);
    }

    @Override
    public void logout() {
        frame_login.setLocationRelativeTo(null);
        frame_login.setVisible(true);
    }

    @Override
    public void login_done(tbl_Teacher acc) {
        MainFrame mainf = new MainFrame();
        mainf.setCallBack(this, acc);
        mainf.setLocationRelativeTo(null);
        mainf.setVisible(true);
        frame_login.setVisible(false);
    }

}
