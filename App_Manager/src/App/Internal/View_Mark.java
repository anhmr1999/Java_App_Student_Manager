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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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
    tbl_Mark mark_edit;

    public View_Mark(Connection conn, tbl_Student s) {
        this.conn = conn;
        this.s = s;
        initComponents();
        Name.setText(s.getName());
        Student_Name.setText("Học sinh " + s.getName());
        RollNo.setText(s.getRoll());
        getListMark(" WHERE Student_ID = " + s.getId());
        getListSubject();
        viewTable();
        setLayer();
        setComboStatus();
    }

    private void getListMark(String check) {
        Mark_Controller MC = new Mark_Controller(conn);
        LM = MC.select(check);
    }

    private void getListSubject() {
        Subject_Controller SC = new Subject_Controller(conn);
        LS = SC.select("");
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

    private void viewTable() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Môn Học");
        dtm.addColumn("Điểm");
        dtm.addColumn("Trạng thái");
        dtm.addColumn("Ghi chú");
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
                                sub.getName(), m.getMark(), "Bị Cấm thi", m.getNote()
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
        jTable.setModel(dtm);
        jTable.setRowHeight(25);
    }

    private void setComboStatus() {
        DefaultComboBoxModel dcms = new DefaultComboBoxModel();
        dcms.addElement("Đã thi");
        dcms.addElement("Cấm thi");
        dcms.addElement("Chưa thi");
        jComboStatus.setModel(dcms);
    }

    private void get_Mark_edit() {
        mark_edit = LM.get(jTable.getSelectedRow());
        if (mark_edit == null) {
            JOptionPane.showMessageDialog(null, "Đã có lỗi ở đâu đóa rồi :))");
        } else {
            for (tbl_Subject subject_Mark_Edit : LS) {
                if (subject_Mark_Edit.getID() == mark_edit.getSubject_ID()) {
                    Subject_Edit_Mark.setText("Môn " + subject_Mark_Edit.getName());
                }
            }
            jMark_Student.setText(String.valueOf(mark_edit.getMark()));
            jNote.setText(mark_edit.getNote());
            jComboStatus.setSelectedIndex(mark_edit.getStatus() - 1);
            try {
                Ex_date.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(mark_edit.getEx_date()));
            } catch (ParseException ex) {
                Logger.getLogger(View_Mark.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void reloadFormEdit() {
        mark_edit = null;
        jMark_Student.setText("0.0");
        jComboStatus.setSelectedIndex(0);
        jNote.setText("");
        Subject_Edit_Mark.setText("Môn học");
        try {
            Ex_date.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2000/02/22"));
        } catch (ParseException ex) {
            Logger.getLogger(View_Mark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveMark() {
        if (mark_edit == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn môn học cần sửa điểm để tiến hành");
        } else {
            float new_mark = Float.parseFloat(jMark_Student.getText());
            int new_Status_Mark = jComboStatus.getSelectedIndex() + 1;
            String new_not_mark = jNote.getText();
            if (new_mark > 10 || new_mark < 0) {
                JOptionPane.showMessageDialog(null, "Điểm không hợp lệ");
            } else if (new_mark != 0 && (new_Status_Mark == 3 || new_Status_Mark == 2)) {
                JOptionPane.showMessageDialog(null, "Cập nhập điểm không chính xác, vui lòng kiểm tra lại");
            } else if (new_not_mark.length() > 490) {
                JOptionPane.showMessageDialog(null, "Ghi chú bạn thêm quá dài, không thể cập nhập");
            } else if ("2000/02/22".equals(new SimpleDateFormat("yyyy/MM/dd").format(Ex_date.getDate()))) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày thi", "Cảnh báo", JOptionPane.ERROR);
            } else {
                mark_edit.setMark(new_mark);
                mark_edit.setNote(new_not_mark);
                mark_edit.setEx_date(new SimpleDateFormat("yyyy/MM/dd").format(Ex_date.getDate()));
                mark_edit.setStatus(new_Status_Mark);
                Mark_Controller MC = new Mark_Controller(conn);
                if (MC.update(mark_edit)) {
                    JOptionPane.showMessageDialog(null, "Bạn đã cập nhập điểm thành công");
                    getListMark(" WHERE Student_ID = " + s.getId());
                    getListSubject();
                    viewTable();
                    reloadFormEdit();
                } else {
                    JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Cảnh báo", JOptionPane.ERROR);
                }
            }
        }
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
        jPanel5 = new javax.swing.JPanel();
        Subject_Edit_Mark = new javax.swing.JLabel();
        Student_Name = new javax.swing.JLabel();
        jTextmark = new javax.swing.JLabel();
        jTextStatusMark = new javax.swing.JLabel();
        jComboStatus = new javax.swing.JComboBox<>();
        jTextNoteMark = new javax.swing.JLabel();
        Save_Edit = new javax.swing.JButton();
        UnchangeMark = new javax.swing.JButton();
        jMark_Student = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jNote = new javax.swing.JEditorPane();
        jTextStatusMark1 = new javax.swing.JLabel();
        Ex_date = new com.toedter.calendar.JDateChooser();

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
        Mark_Update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mark_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mark_UpdateActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Exit.setText("Hủy Bỏ");
        Exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                .addGap(190, 190, 190))
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
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
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
                .addContainerGap(180, Short.MAX_VALUE))
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
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jComboBox.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Chưa Thi", "Dưới 5", "Từ 5 đến 6.5", "Từ 6.5 đến 8", "Từ 8-10" }));

        Filter.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Filter.setText("Lọc danh sách");
        Filter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });

        Filter1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Filter1.setText("Cập Nhập Lại Danh Sách ");
        Filter1.setToolTipText("Cập nhập lại danh sách điểm môn cho sinh viên");
        Filter1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Sửa điểm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16))); // NOI18N

        Subject_Edit_Mark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Subject_Edit_Mark.setText("Môn học ");

        Student_Name.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Student_Name.setText("Học sinh Nguyễn Văn A");

        jTextmark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jTextmark.setText("Điểm môn:");

        jTextStatusMark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jTextStatusMark.setText("Trạng thái:");

        jComboStatus.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jComboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextNoteMark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jTextNoteMark.setText("Ghi chú:");

        Save_Edit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Save_Edit.setText("Lưu chỉnh sửa");
        Save_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_EditActionPerformed(evt);
            }
        });

        UnchangeMark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        UnchangeMark.setText("Hủy bỏ");
        UnchangeMark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnchangeMarkActionPerformed(evt);
            }
        });

        jMark_Student.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.0"))));
        jMark_Student.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jMark_Student.setText("0.0");
        jMark_Student.setToolTipText("");
        jMark_Student.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jMark_Student.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMark_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMark_StudentActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jNote);

        jTextStatusMark1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jTextStatusMark1.setText("Ngày thi:");

        Ex_date.setDateFormatString("dd/MM/yyyy");
        Ex_date.setPreferredSize(new java.awt.Dimension(69, 24));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Student_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Subject_Edit_Mark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextmark)
                            .addComponent(jTextStatusMark))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jMark_Student)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addComponent(UnchangeMark)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Save_Edit))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextStatusMark1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Ex_date, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextNoteMark)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(Student_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Subject_Edit_Mark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextmark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMark_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextStatusMark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Ex_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextStatusMark1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jTextNoteMark, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UnchangeMark, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Save_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout View_MarkLayout = new javax.swing.GroupLayout(View_Mark);
        View_Mark.setLayout(View_MarkLayout);
        View_MarkLayout.setHorizontalGroup(
            View_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(View_MarkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        View_MarkLayout.setVerticalGroup(
            View_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, View_MarkLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(View_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
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
            if (sub.getStatus() == 1) {
                tbl_Mark m = new tbl_Mark(s.getId(), sub.getID(), 0, 3, "", "2000/02/22");
                if (MC.insert(m) == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        }
        if (check) {
            JOptionPane.showMessageDialog(null, "Bạn đã cập nhập điểm môn thành công");
            getListMark("WHERE Student_ID = " + s.getId());
            viewTable();
            setLayer();
        }
    }//GEN-LAST:event_Mark_UpdateActionPerformed

    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterActionPerformed
        int select_index = jComboBox.getSelectedIndex();
        switch (select_index) {
            case 0:
                getListMark(" WHERE Student_ID = " + s.getId());
                break;
            case 1:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Status = 3");
                break;
            case 2:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Mark <5 AND Status = 1");
                break;
            case 3:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Mark >=5 AND Mark < 6.5");
                break;
            case 4:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Mark >=6.5 AND Mark <8");
                break;
            default:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Mark >=8");
                break;
        }
        viewTable();
    }//GEN-LAST:event_FilterActionPerformed

    private void Filter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Filter1ActionPerformed
        Mark_Controller MC = new Mark_Controller(conn);
        boolean check = false;
        for (tbl_Subject sub : LS) {
            if (sub.getStatus() == 1) {
                tbl_Mark m = new tbl_Mark(s.getId(), sub.getID(), 0, 3, "", "2000/02/22");
                if (MC.insert(m) == 1) {
                    check = true;
                }
            }
        }
        if (check) {
            JOptionPane.showMessageDialog(null, "Bạn đã cập nhập điểm môn thành công");
            getListMark(" WHERE Student_ID = " + s.getId());
            viewTable();
            setLayer();
        }
    }//GEN-LAST:event_Filter1ActionPerformed

    private void jTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMousePressed
        get_Mark_edit();
    }//GEN-LAST:event_jTableMousePressed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        get_Mark_edit();
    }//GEN-LAST:event_jTableMouseClicked

    private void UnchangeMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UnchangeMarkActionPerformed
        reloadFormEdit();
    }//GEN-LAST:event_UnchangeMarkActionPerformed

    private void Save_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_EditActionPerformed
        saveMark();
    }//GEN-LAST:event_Save_EditActionPerformed

    private void jMark_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMark_StudentActionPerformed
        jComboStatus.setSelectedIndex(0);
        saveMark();
    }//GEN-LAST:event_jMark_StudentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Ex_date;
    private javax.swing.JButton Exit;
    private javax.swing.JButton Filter;
    private javax.swing.JButton Filter1;
    private javax.swing.JButton Mark_Update;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel RollNo;
    private javax.swing.JButton Save_Edit;
    private javax.swing.JLabel Student_Name;
    private javax.swing.JLabel Subject_Edit_Mark;
    private javax.swing.JButton UnchangeMark;
    private javax.swing.JPanel Update_Mark;
    private javax.swing.JPanel View_Mark;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JComboBox<String> jComboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane;
    private javax.swing.JFormattedTextField jMark_Student;
    private javax.swing.JEditorPane jNote;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel jTextNoteMark;
    private javax.swing.JLabel jTextStatusMark;
    private javax.swing.JLabel jTextStatusMark1;
    private javax.swing.JLabel jTextmark;
    // End of variables declaration//GEN-END:variables
}
