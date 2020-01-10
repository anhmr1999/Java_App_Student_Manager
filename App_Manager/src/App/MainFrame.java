/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import App.connect.ConnectDB;
import App.model.tbl_Teacher;
import App.panel.Panel_Chart;
import App.panel.Panel_Home;
import App.panel.Panel_Student;
import App.panel.Panel_Subject;
import App.panel.Panel_User;
import java.awt.Cursor;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author PC Hoang Anh
 */
public class MainFrame extends javax.swing.JFrame implements Panel_Home.Call_Back {

    ConnectDB c = new ConnectDB();
    Cursor cur = new Cursor(Cursor.HAND_CURSOR);
    Connection conn = c.connect();
    tbl_Teacher acc = null;

    /**
     * Creates new form MainFrame
     */
    public interface Call_Logout {

        public void logout();
    };
    Call_Logout CL;

    public void setCallBack(Call_Logout CL, tbl_Teacher acc) {
        this.CL = CL;
        this.acc = acc;
        Panel_Home home = new Panel_Home(conn, acc, this);
        setLayer(home);
    }

    public MainFrame() {
        initComponents();
    }

    public void setLayer(JPanel panel) {
        jLayeredPane.removeAll();
        jLayeredPane.add(panel);
        jLayeredPane.repaint();
        jLayeredPane.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar2 = new javax.swing.JToolBar();
        jLayeredPane = new javax.swing.JLayeredPane();
        jPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jToolBar = new javax.swing.JToolBar();
        Home = new javax.swing.JButton();
        User = new javax.swing.JButton();
        Student = new javax.swing.JButton();
        Subject = new javax.swing.JButton();
        Chart = new javax.swing.JButton();
        jToolBar4 = new javax.swing.JToolBar();
        logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý sinh viên");

        jToolBar2.setBorder(null);
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jLayeredPane.setBackground(new java.awt.Color(204, 255, 204));
        jLayeredPane.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 955, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );

        jLayeredPane.add(jPanel, "card2");

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setToolTipText("");

        jToolBar.setRollover(true);

        Home.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/home-icon.png"))); // NOI18N
        Home.setText("Trang chủ");
        Home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home.setFocusable(false);
        Home.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Home.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
        });
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });
        jToolBar.add(Home);

        User.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/user-icon.png"))); // NOI18N
        User.setText("Quản lý tài khoản");
        User.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        User.setFocusable(false);
        User.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        User.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        User.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserMouseClicked(evt);
            }
        });
        User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserActionPerformed(evt);
            }
        });
        jToolBar.add(User);

        Student.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Student.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/student-icon.png"))); // NOI18N
        Student.setText("Quản lý sinh viên");
        Student.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Student.setFocusable(false);
        Student.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Student.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Student.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentMouseClicked(evt);
            }
        });
        Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentActionPerformed(evt);
            }
        });
        jToolBar.add(Student);

        Subject.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Subject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Subjec-icon.png"))); // NOI18N
        Subject.setText("Quản lý môn học");
        Subject.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Subject.setFocusable(false);
        Subject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Subject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Subject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SubjectMouseClicked(evt);
            }
        });
        Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectActionPerformed(evt);
            }
        });
        jToolBar.add(Subject);

        Chart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Chart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/level-icon.png"))); // NOI18N
        Chart.setText("Thống kê");
        Chart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Chart.setFocusable(false);
        Chart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Chart.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Chart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChartMouseClicked(evt);
            }
        });
        Chart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChartActionPerformed(evt);
            }
        });
        jToolBar.add(Chart);

        jToolBar4.setFloatable(false);
        jToolBar4.setRollover(true);

        logout.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Lock-icon.png"))); // NOI18N
        logout.setText("Đăng xuất");
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.setFocusable(false);
        logout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jToolBar4.add(logout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked

    }//GEN-LAST:event_HomeMouseClicked

    private void UserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserMouseClicked
        setLayer(new Panel_User(acc, conn));
    }//GEN-LAST:event_UserMouseClicked

    private void StudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentMouseClicked

    }//GEN-LAST:event_StudentMouseClicked

    private void SubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SubjectMouseClicked

    }//GEN-LAST:event_SubjectMouseClicked

    private void ChartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChartMouseClicked

    }//GEN-LAST:event_ChartMouseClicked

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Cảm ơn đã sử dụng dịch vụ");
        acc = null;
        CL.logout();
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserActionPerformed
        setLayer(new Panel_User(acc, conn));
    }//GEN-LAST:event_UserActionPerformed

    private void StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentActionPerformed
        setLayer(new Panel_Student(acc, conn));
    }//GEN-LAST:event_StudentActionPerformed

    private void SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectActionPerformed
        Panel_Subject Sub = new Panel_Subject(conn, acc);
        setLayer(Sub);
    }//GEN-LAST:event_SubjectActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        Panel_Home home = new Panel_Home(conn, acc, this);
        setLayer(home);
    }//GEN-LAST:event_HomeActionPerformed

    private void ChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChartActionPerformed
        Panel_Chart PC = new Panel_Chart(conn, acc);
        setLayer(PC);
    }//GEN-LAST:event_ChartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Chart;
    private javax.swing.JButton Home;
    private javax.swing.JButton Student;
    private javax.swing.JButton Subject;
    private javax.swing.JButton User;
    private javax.swing.JLayeredPane jLayeredPane;
    private javax.swing.JPanel jPanel;
    private javax.swing.JToolBar jToolBar;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JButton logout;
    // End of variables declaration//GEN-END:variables

    @Override
    public void view_Teacher() {
        setLayer(new Panel_User(acc, conn));
    }

    @Override
    public void view_Student() {
        setLayer(new Panel_Student(acc, conn));
    }

    @Override
    public void view_Subject() {
        Panel_Subject Sub = new Panel_Subject(conn, acc);
        setLayer(Sub);
    }

    @Override
    public void view_Chart() {
        Panel_Chart PC = new Panel_Chart(conn, acc);
        setLayer(PC);
    }

}
