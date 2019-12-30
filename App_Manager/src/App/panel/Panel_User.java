/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.panel;

import App.Internal.Edit_Teacher;
import App.Internal.Edit_Teacher.ReloadTable;
import App.controller.Role_Controller;
import App.controller.Teacher_Controller;
import App.model.tbl_Role;
import App.model.tbl_Teacher;
import java.sql.Connection;
import java.text.DateFormat;
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
public class Panel_User extends javax.swing.JPanel implements ReloadTable {

    /**
     * Creates new form Panel_User
     */
    tbl_Teacher acc;
    Connection conn;
    List<tbl_Teacher> LT;
    List<tbl_Role> LR;

    public Panel_User(tbl_Teacher acc, Connection cnn) {
        this.acc = acc;
        this.conn = cnn;
        if (acc == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập để sử dụng chức năng");
        } else if (conn == null) {
            JOptionPane.showMessageDialog(null, "Kết nối đã có lỗi, vui lòng thử lại");
        } else {
            initComponents();
            admin.setText("Xin chào " + acc.getName());
            getRole();
            load_table("");
            set_Combobox();
            jStatus_1.doClick();
            try {
                jDate.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2000/01/01"));
            } catch (ParseException ex) {
                Logger.getLogger(Panel_User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void getRole() {
        Role_Controller RC = new Role_Controller(conn);
        LR = RC.select();
    }

    private void load_table(String check) {
        Teacher_Controller TC = new Teacher_Controller(conn);
        LT = TC.select(check);
        DefaultTableModel dtm = new DefaultTableModel();
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            dtm.addColumn("Tên Giảng Viên");
            dtm.addColumn("Số điện thoại");
            dtm.addColumn("Địa chỉ Email");
            dtm.addColumn("Mật khẩu");
            dtm.addColumn("Địa chỉ");
            dtm.addColumn("Ngày Sinh");
            dtm.addColumn("Trạng thái");
            dtm.addColumn("Chức vụ");
            for (tbl_Teacher t : LT) {
                for (tbl_Role r : LR) {
                    if (t.getRole_ID() == r.getId()) {
                        if (t.getStatus() == 1) {
                            Object o[] = {t.getName(), t.getPhone(), t.getEmail(), t.getPass(), t.getAddress(), t.getDOB(), "Đang Giảng Dạy", r.getName()};
                            dtm.addRow(o);
                        } else {
                            Object o[] = {t.getName(), t.getPhone(), t.getEmail(), t.getPass(), t.getAddress(), t.getDOB(), "Đã Về Hưu", r.getName()};
                            dtm.addRow(o);
                        }
                    }
                }
            }
        } else {
            dtm.addColumn("Tên Giảng Viên");
            dtm.addColumn("Số điện thoại");
            dtm.addColumn("Địa chỉ Email");
            dtm.addColumn("Địa chỉ");
            dtm.addColumn("Ngày Sinh");
            dtm.addColumn("Trạng thái");
            dtm.addColumn("Chức vụ");
            for (tbl_Teacher t : LT) {
                for (tbl_Role r : LR) {
                    if (t.getRole_ID() == r.getId()) {
                        if (t.getStatus() == 1) {
                            Object o[] = {t.getName(), t.getPhone(), t.getEmail(), t.getAddress(), t.getDOB(), "Đang Giảng Dạy", r.getName()};
                            dtm.addRow(o);
                        } else {
                            Object o[] = {t.getName(), t.getPhone(), t.getEmail(), t.getAddress(), t.getDOB(), "Đã Về Hưu", r.getName()};
                            dtm.addRow(o);
                        }
                    }
                }
            }
        }
        jTable_Teacher.setModel(dtm);
    }

    private void set_Combobox() {
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        if (acc.getRole_ID() == 1) {
            for (tbl_Role r : LR) {
                dcm.addElement(r.getName());
            }
        } else {
            for (int i = 0; i < LR.size(); i++) {
                if (i >= 2) {
                    tbl_Role r = LR.get(i);
                    dcm.addElement(r.getName());
                }
            }
        }
        jRole.setModel(dcm);
    }

    private boolean check_Mail_Phone(String mail, String phone) {
        boolean check = false;
        for (tbl_Teacher t : LT) {
            if (t.getPhone().equals(phone) || t.getEmail().equals(mail)) {
                check = true;
            }
        }
        return check;
    }
    
    private void refesh_Form(){
        jName.setText("");
        jPhone.setText("");
        jEmail.setText("");
        jAddress.setText("");
        try {
            jDate.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2000/01/01"));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_User.class.getName()).log(Level.SEVERE, null, ex);
        }
        jRole.setSelectedIndex(0);
        jStatus_1.doClick();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Status = new javax.swing.ButtonGroup();
        jPopupMenu = new javax.swing.JPopupMenu();
        Edit_Teacher = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        admin = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jDesktopPane = new javax.swing.JDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        Seach = new javax.swing.JTextField();
        Reload = new javax.swing.JButton();
        Seach_Teacher = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Teacher = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPhone = new javax.swing.JTextField();
        jName = new javax.swing.JTextField();
        jAddress = new javax.swing.JTextField();
        jEmail = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
        jRole = new javax.swing.JComboBox<>();
        jStatus_1 = new javax.swing.JRadioButton();
        jStatus_2 = new javax.swing.JRadioButton();
        jPassword = new javax.swing.JPasswordField();
        showPass = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        New_Teacher_Add = new javax.swing.JButton();
        Refesh_Form_Insert = new javax.swing.JButton();

        Edit_Teacher.setText("Sửa");
        Edit_Teacher.setToolTipText("Sửa thông tin giảng viên");
        Edit_Teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Edit_TeacherActionPerformed(evt);
            }
        });
        jPopupMenu.add(Edit_Teacher);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        admin.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/user-icon.png"))); // NOI18N
        admin.setText("Hi Admin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(admin, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(admin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tài khoản người dùng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        Seach.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        Reload.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Reload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/reset.png"))); // NOI18N
        Reload.setText("Tải lại danh sách");
        Reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReloadActionPerformed(evt);
            }
        });

        Seach_Teacher.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Seach_Teacher.setText("Tìm kiếm");
        Seach_Teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Seach_TeacherActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Tên giáo viên:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Seach, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Seach_Teacher, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Reload, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Seach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Seach_Teacher, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reload, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jTable_Teacher.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable_Teacher.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_Teacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable_TeacherMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Teacher);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tài khoản", jPanel4);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tài khoản mới");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Họ và tên:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("Số điện thoại: ");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Email:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel10.setText("Địa chỉ:");

        jPhone.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jAddress.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jEmail.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jAddress))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jEmail))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPhone))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jName, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel12);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setText("Mật khẩu:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel11.setText("Ngày sinh:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel12.setText("Role:");

        jDate.setDateFormatString("dd/MM/yyyy");
        jDate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jRole.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Status.add(jStatus_1);
        jStatus_1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jStatus_1.setText("Đang giảng dạy");

        Status.add(jStatus_2);
        jStatus_2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jStatus_2.setText("Đã về hưu");
        jStatus_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStatus_2ActionPerformed(evt);
            }
        });

        jPassword.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPassword.setEchoChar('*');

        showPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/eye-icon.png"))); // NOI18N
        showPass.setToolTipText("hiển thị mật khẩu\n");
        showPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPass, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jStatus_1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jStatus_2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(showPass, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRole, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jStatus_1)
                    .addComponent(jStatus_2))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel13);

        New_Teacher_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/add.png"))); // NOI18N
        New_Teacher_Add.setText("Thêm mới");
        New_Teacher_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                New_Teacher_AddActionPerformed(evt);
            }
        });
        jPanel15.add(New_Teacher_Add);

        Refesh_Form_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/reset.png"))); // NOI18N
        Refesh_Form_Insert.setText("Làm lại");
        Refesh_Form_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refesh_Form_InsertActionPerformed(evt);
            }
        });
        jPanel15.add(Refesh_Form_Insert);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Thêm mới tài khoản", jPanel7);

        jDesktopPane.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1001, Short.MAX_VALUE)
            .addGroup(jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE))
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
            .addGroup(jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jDesktopPane))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jDesktopPane))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jStatus_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStatus_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStatus_2ActionPerformed

    private void Refesh_Form_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refesh_Form_InsertActionPerformed
        refesh_Form();
    }//GEN-LAST:event_Refesh_Form_InsertActionPerformed

    private void ReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReloadActionPerformed
        load_table("");
    }//GEN-LAST:event_ReloadActionPerformed

    private void Seach_TeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Seach_TeacherActionPerformed
        String seach = Seach.getText();
        load_table(" where Name like N'%" + seach + "%'");
    }//GEN-LAST:event_Seach_TeacherActionPerformed

    private void New_Teacher_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_New_Teacher_AddActionPerformed
        String regex_Phone = "^0[0-9]{9,10}$";
        if (acc.getRole_ID() == 4) {
            JOptionPane.showMessageDialog(null, "Xin lỗi, bạn không có quyền thêm tài khoản");
        } else {
            String new_name = jName.getText();
            String new_Phone = jPhone.getText();
            String new_Email = jEmail.getText();
            String new_pass = String.valueOf(jPassword.getPassword());
            String new_Address = jAddress.getText();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String new_DOB = sdf.format(jDate.getDate());
            int selectRole = jRole.getSelectedIndex();
            int new_id_Role = LR.get(selectRole+2).getId();
            int new_status;
            if (jStatus_1.isSelected()) {
                new_status = 1;
            } else {
                new_status = 2;
            }
            if (new_name.length() == 0 || new_Phone.length() == 0 || new_Email.length() == 0 || new_Address.length() == 0 || new_DOB.length() == 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin vào các trường để tiến hành");
            } else if (new_Phone.length() > 11 || new_Phone.length() < 10 || regex_Phone.matches(new_Phone)) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số điện thoại để tiếp tục");
            } else if (check_Mail_Phone(new_Email, new_Phone)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại hoặc Email đã được sử dụng!");
            } else {
                int choose = JOptionPane.showConfirmDialog(null, "Bạn có chắc sẽ thêm một tài khoản với các dữ liệu trên?");
                if (choose == JOptionPane.YES_OPTION) {
                    tbl_Teacher newTeacher = new tbl_Teacher(new_name, new_Phone, new_Email, new_pass, new_Address, new_DOB, new_status, new_id_Role);
//                    System.out.println(newTeacher.toString());
                    Teacher_Controller TC = new Teacher_Controller(conn);
                    if (TC.insert(newTeacher) == 1) {
                        JOptionPane.showMessageDialog(null, "Bạn đã thêm tài khoản thành công");
                        load_table("");
                        refesh_Form();
                    } else {
                        JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra! \nVui lòng kiểm tra lại Email hoặc SĐT");
                    }
                }

            }
        }
    }//GEN-LAST:event_New_Teacher_AddActionPerformed

    private void jTable_TeacherMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_TeacherMousePressed
        jPopupMenu.show(jTable_Teacher, evt.getX(), evt.getY());
    }//GEN-LAST:event_jTable_TeacherMousePressed

    private void Edit_TeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Edit_TeacherActionPerformed
        tbl_Teacher teacher_edit = LT.get(jTable_Teacher.getSelectedRow());
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2 || teacher_edit.getID() == acc.getID()) {
            Edit_Teacher et = new Edit_Teacher(teacher_edit, conn, this);
            jDesktopPane.add(et);
            et.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Bạn không đủ quyền hạn để sử dụng chức năng này");
        }
    }//GEN-LAST:event_Edit_TeacherActionPerformed

    private void showPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassActionPerformed
        jPassword.setEchoChar((char) 0);
    }//GEN-LAST:event_showPassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Edit_Teacher;
    private javax.swing.JButton New_Teacher_Add;
    private javax.swing.JButton Refesh_Form_Insert;
    private javax.swing.JButton Reload;
    private javax.swing.JTextField Seach;
    private javax.swing.JButton Seach_Teacher;
    private javax.swing.ButtonGroup Status;
    private javax.swing.JLabel admin;
    private javax.swing.JTextField jAddress;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JTextField jEmail;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JTextField jPhone;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JComboBox<String> jRole;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jStatus_1;
    private javax.swing.JRadioButton jStatus_2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable_Teacher;
    private javax.swing.JButton showPass;
    // End of variables declaration//GEN-END:variables

    @Override
    public void loadData() {
        load_table("");
    }
}
