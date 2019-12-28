/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Internal;

import App.controller.Mark_Controller;
import App.controller.Subject_Controller;
import App.model.tbl_Mark;
import App.model.tbl_Student;
import App.model.tbl_Subject;
import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC Hoang Anh
 */
public class View_Mark extends javax.swing.JInternalFrame {

    /**
     * Creates new form View_Mark
     */
    Connection conn;
    tbl_Student s;
    List<tbl_Mark> LM;
    List<tbl_Subject> LS;

    public View_Mark(Connection conn, tbl_Student s) {
        this.conn = conn;
        this.s = s;
        initComponents();
        Name.setText(s.getName());
        RollNo.setText(s.getRoll());
        getListMark();
        getListSubject();
        viewTable(0);
        setLayer();
    }

    private void getListMark() {
        Mark_Controller MC = new Mark_Controller(conn);
        LM = MC.select(s.getId());
    }

    private void getListSubject() {
        Subject_Controller SC = new Subject_Controller(conn);
        LS = SC.select();
    }

    private void setLayer() {
        jLayeredPane.removeAll();
        if (LM.size() == 0) {
            jLayeredPane.add(Update_Mark);
        } else {
            jLayeredPane.add(View_Mark);
        }
        jLayeredPane.repaint();
        jLayeredPane.revalidate();
    }

    private void viewTable(int num) {
        getListMark();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Môn Học");
        dtm.addColumn("Điểm");
        dtm.addColumn("Trạng thái");
        dtm.addColumn("Ghi chú");
        switch (num) {
            case 0:
                for (tbl_Mark m : LM) {
                    for (tbl_Subject sub : LS) {
                        if (m.getSubject_ID() == sub.getID()) {
                            switch (m.getStatus()) {
                                case 1: {
                                    Object o[] = {
                                        sub.getName(), m.getMark(), "Đã Thi", m.getNote()
                                    };
                                    dtm.addRow(o);
                                    break;
                                }
                                case 2: {
                                    Object o[] = {
                                        sub.getName(), m.getMark(), "Đang nợ môn", m.getNote()
                                    };
                                    dtm.addRow(o);
                                    break;
                                }
                                default: {
                                    Object o[] = {
                                        sub.getName(), m.getMark(), "Chưa thi", m.getNote()
                                    };
                                    dtm.addRow(o);
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            case 1:
                for (tbl_Mark m : LM) {
                    if (m.getStatus() == 3) {
                        for (tbl_Subject sub : LS) {
                            if (m.getSubject_ID() == sub.getID()) {
                                Object o[] = {
                                    sub.getName(), m.getMark(), "Chưa thi", m.getNote()
                                };
                                dtm.addRow(o);
                            }
                        }
                    }
                }
                break;
            case 2:
                for (tbl_Mark m : LM) {
                    if (m.getMark() < 5 && m.getMark() > 0) {
                        for (tbl_Subject sub : LS) {
                            if (m.getSubject_ID() == sub.getID()) {
                                Object o[] = {
                                    sub.getName(), m.getMark(), "Đã thi", m.getNote()
                                };
                                dtm.addRow(o);
                            }
                        }
                    }
                }
                break;
            case 3:
                for (tbl_Mark m : LM) {
                    if (m.getMark() <= 6.5 && m.getMark() >= 5) {
                        for (tbl_Subject sub : LS) {
                            if (m.getSubject_ID() == sub.getID()) {
                                Object o[] = {
                                    sub.getName(), m.getMark(), "Đã thi", m.getNote()
                                };
                                dtm.addRow(o);
                            }
                        }
                    }
                }
                break;
            case 4:
                for (tbl_Mark m : LM) {
                    if (m.getMark() <= 8 && m.getMark() >= 6.5) {
                        for (tbl_Subject sub : LS) {
                            if (m.getSubject_ID() == sub.getID()) {
                                Object o[] = {
                                    sub.getName(), m.getMark(), "Đã thi", m.getNote()
                                };
                                dtm.addRow(o);
                            }
                        }
                    }
                }
                break;
            case 5:
                for (tbl_Mark m : LM) {
                    if (m.getMark() <= 10 && m.getMark() >= 8) {
                        for (tbl_Subject sub : LS) {
                            if (m.getSubject_ID() == sub.getID()) {
                                Object o[] = {
                                    sub.getName(), m.getMark(), "Đã thi", m.getNote()
                                };
                                dtm.addRow(o);
                            }
                        }
                    }
                }
                break;
            default:
                for (tbl_Mark m : LM) {
                    if (m.getMark() > 10) {
                        for (tbl_Subject sub : LS) {
                            if (m.getSubject_ID() == sub.getID()) {
                                Object o[] = {
                                    sub.getName(), m.getMark(), "Đã thi", m.getNote()
                                };
                                dtm.addRow(o);
                            }
                        }
                    }
                }
                break;
        }
        jTable.setModel(dtm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        RollNo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLayeredPane = new javax.swing.JLayeredPane();
        Update_Mark = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Mark_Update = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        View_Mark = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jComboBox = new javax.swing.JComboBox<>();
        Filter = new javax.swing.JButton();
        Filter1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Xem điểm sinh viên");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Học Sinh:");

        Name.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Name.setText("Nguyễn Văn A");

        RollNo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        RollNo.setText("21212");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Mã Sinh Viên:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RollNo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Name)
                    .addComponent(RollNo)
                    .addComponent(jLabel4)))
        );

        jLayeredPane.setLayout(new java.awt.CardLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Học Sinh + Tên Chưa được cập nhập danh sách điểm môn");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Vui Lòng Ấn \"Cập Nhập Điểm Môn\" để thêm điểm các môn vào danh sách");

        Mark_Update.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Mark_Update.setText("Cập Nhập Điểm Môn");
        Mark_Update.setToolTipText("Cập nhập danh sách điểm môn học của sinh viên");
        Mark_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mark_UpdateActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Exit.setText("Hủy Bỏ");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Mark_Update)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mark_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Update_MarkLayout = new javax.swing.GroupLayout(Update_Mark);
        Update_Mark.setLayout(Update_MarkLayout);
        Update_MarkLayout.setHorizontalGroup(
            Update_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Update_MarkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Update_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Update_MarkLayout.setVerticalGroup(
            Update_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Update_MarkLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        jLayeredPane.add(Update_Mark, "card2");

        jTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        jComboBox.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Chưa Thi", "Dưới 5", "Từ 5 đến 6.5", "Từ 6.5 đến 8", "Từ 8-10" }));

        Filter.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Filter.setText("Lọc danh sách");
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });

        Filter1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Filter1.setText("Cập Nhập Lại Danh Sách\n");
        Filter1.setToolTipText("Cập nhập lại danh sách điểm môn cho sinh viên");
        Filter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Filter1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Filter1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Filter1)
                    .addComponent(Filter)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout View_MarkLayout = new javax.swing.GroupLayout(View_Mark);
        View_Mark.setLayout(View_MarkLayout);
        View_MarkLayout.setHorizontalGroup(
            View_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, View_MarkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        View_MarkLayout.setVerticalGroup(
            View_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, View_MarkLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
        );

        jLayeredPane.add(View_Mark, "card3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_ExitActionPerformed

    private void Mark_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mark_UpdateActionPerformed
        Mark_Controller MC = new Mark_Controller(conn);
        boolean check = false;
        for (tbl_Subject sub : LS) {
            tbl_Mark m = new tbl_Mark(s.getId(), sub.getID(), 0, 3, "");
            if (MC.insert(m) == 1) {
                check = true;
            } else {
                check = false;
            }
        }
        if (check) {
            JOptionPane.showMessageDialog(null, "Bạn đã cập nhập điểm môn thành công");
            getListMark();
            viewTable(0);
            setLayer();
        }
    }//GEN-LAST:event_Mark_UpdateActionPerformed

    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterActionPerformed
        int select_index = jComboBox.getSelectedIndex();
        viewTable(select_index);
    }//GEN-LAST:event_FilterActionPerformed

    private void Filter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Filter1ActionPerformed
        Mark_Controller MC = new Mark_Controller(conn);
        boolean check = false;
        for (tbl_Subject sub : LS) {
            tbl_Mark m = new tbl_Mark(s.getId(), sub.getID(), 0, 3, "");
            if (MC.insert(m) == 1) {
                check = true;
            }
        }
        if (check) {
            JOptionPane.showMessageDialog(null, "Bạn đã cập nhập điểm môn thành công");
            getListMark();
            viewTable(0);
            setLayer();
        }
    }//GEN-LAST:event_Filter1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit;
    private javax.swing.JButton Filter;
    private javax.swing.JButton Filter1;
    private javax.swing.JButton Mark_Update;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel RollNo;
    private javax.swing.JPanel Update_Mark;
    private javax.swing.JPanel View_Mark;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
