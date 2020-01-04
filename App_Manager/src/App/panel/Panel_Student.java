/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.panel;

import App.Internal.Edit_Student;
import App.Internal.View_Info_Student;
import App.Internal.View_Mark;
import App.controller.Class_Controller;
import App.controller.Mark_Controller;
import App.controller.Student_Controller;
import App.controller.Subject_Controller;
import App.model.tbl_Class;
import App.model.tbl_Mark;
import App.model.tbl_Student;
import App.model.tbl_Subject;
import App.model.tbl_Teacher;
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
 * @author Hai Anh PC
 */
public class Panel_Student extends javax.swing.JPanel implements Edit_Student.reload {

    /**
     * Creates new form Panel_Student
     */
    tbl_Teacher acc;
    Connection conn;
    List<tbl_Student> LS;
    List<tbl_Class> LC;

    public Panel_Student(tbl_Teacher acc, Connection conn) {
        this.acc = acc;
        this.conn = conn;
        initComponents();
        if (acc == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập để sử dụng chức năng");
        } else if (conn == null) {
            JOptionPane.showMessageDialog(null, "Kết nối đã có lỗi, vui lòng thử lại");
        } else {
            initComponents();
            setCombobox();
            if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
                getStudent("");
            } else {
                getStudent(" where Class_ID in (select ID from tbl_Class where Teacher_ID = " + acc.getID() + " )");
            }
            loadTable();
            admin.setText("Xin chào " + acc.getName());
            Nam.doClick();
            setRollNoNewStudent();
            try {
                jDate.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2000/01/01"));
            } catch (ParseException ex) {
                Logger.getLogger(Panel_User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setCombobox() {
        Class_Controller CC = new Class_Controller(conn);
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            LC = CC.select("");
        } else {
            LC = CC.select(" WHERE Teacher_ID = " + acc.getID());
        }
        DefaultComboBoxModel dcm_Class = new DefaultComboBoxModel();
        DefaultComboBoxModel dcm_Seach = new DefaultComboBoxModel();
        dcm_Seach.addElement("Tất cả");
        for (tbl_Class c : LC) {
            dcm_Class.addElement(c.getName());
            dcm_Seach.addElement(c.getName());
        }
        Class_Seach.setModel(dcm_Seach);
        JClass_Student.setModel(dcm_Class);
    }

    private void getStudent(String check) {
        Student_Controller SC = new Student_Controller(conn);
        LS = SC.select(check);
    }

    private void loadTable() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã SV");
        dtm.addColumn("Tên SV");
        dtm.addColumn("Địa chỉ");
        dtm.addColumn("Trạng thái");
        dtm.addColumn("Lớp");
        for (tbl_Student s : LS) {
            for (tbl_Class c : LC) {
                if (c.getId() == s.getClass_ID()) {
                    if (s.getStatus() == 1) {
                        Object o[] = {
                            s.getRoll(), s.getName(), s.getAddress(), "Đang học", c.getName()
                        };
                        dtm.addRow(o);
                    } else if (s.getStatus() == 2) {
                        Object o[] = {
                            s.getRoll(), s.getName(), s.getAddress(), "Đang bảo lưu", c.getName()
                        };
                        dtm.addRow(o);
                    } else if (s.getStatus() == 3) {
                        Object o[] = {
                            s.getRoll(), s.getName(), s.getAddress(), "Đã bỏ học", c.getName()
                        };
                        dtm.addRow(o);
                    } else {
                        Object o[] = {
                            s.getRoll(), s.getName(), s.getAddress(), "Đã tốt nghiệp", c.getName()
                        };
                        dtm.addRow(o);
                    }
                }
            }
        }
        Data_Table.setModel(dtm);
    }

    private void setRollNoNewStudent() {
        int ID = 0;
        for (tbl_Student rollS : LS) {
            ID = Integer.valueOf(rollS.getRoll().substring(1));
        }
        ID = ID + 1;
        if (ID < 10) {
            JRoll.setText("S000" + ID);
        } else if (ID >= 10 && ID < 100) {
            JRoll.setText("S00" + ID);
        } else if (ID >= 100 && ID < 1000) {
            JRoll.setText("S0" + ID);
        } else if (ID >= 1000) {
            JRoll.setText("S" + ID);
        }
    }

    private boolean check_Mail_Phone(String email, String phone) {
        Student_Controller SC = new Student_Controller(conn);
        List<tbl_Student> LS_check = SC.select(" WHERE Email = N'" + email + "' OR Phone = N'" + phone + "'");
        boolean check = LS_check.size() > 0;
        return check;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Gender = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        View = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenuItem();
        Show_mark = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        admin = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jDesktopPane = new javax.swing.JDesktopPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Seach = new javax.swing.JTextField();
        Button_Seach = new javax.swing.JButton();
        Class_Seach = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Data_Table = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JName = new javax.swing.JTextField();
        jDate = new com.toedter.calendar.JDateChooser();
        Nam = new javax.swing.JRadioButton();
        Nu = new javax.swing.JRadioButton();
        Unisex = new javax.swing.JRadioButton();
        JClass_Student = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        Refesh = new javax.swing.JButton();
        add = new javax.swing.JButton();
        JRoll = new javax.swing.JTextField();
        JEmail = new javax.swing.JTextField();
        JAdress = new javax.swing.JTextField();
        Update_Mark_Student = new javax.swing.JButton();
        JPhone = new javax.swing.JTextField();

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(565, 150));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tài khoản người dùng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(48, 48, 48))
        );

        View.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        View.setText("Xem ");
        View.setToolTipText("Xem đầy đủ thông tin");
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewActionPerformed(evt);
            }
        });
        jPopupMenu1.add(View);

        Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Edit.setText("Sửa ");
        Edit.setToolTipText("Sửa thông tin sinh viên");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Edit);

        Show_mark.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Show_mark.setText("Xem điểm");
        Show_mark.setToolTipText("");
        Show_mark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Show_markActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Show_mark);

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 150));

        admin.setBackground(new java.awt.Color(204, 204, 255));
        admin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/user-icon.png"))); // NOI18N
        admin.setText("Xin chào admin");
        admin.setToolTipText("");
        admin.setPreferredSize(new java.awt.Dimension(103, 50));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(admin, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admin, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(565, 150));

        jLabel4.setBackground(new java.awt.Color(204, 204, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Quản lí sinh viên");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Tên sinh viên:");

        Seach.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Button_Seach.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Button_Seach.setText("Tìm kiếm sinh viên");
        Button_Seach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SeachActionPerformed(evt);
            }
        });

        Class_Seach.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Class_Seach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jButton1.setText("Tải lại danh sách");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Seach, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Class_Seach, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Button_Seach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Button_Seach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Class_Seach)
                    .addComponent(Seach, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(5, 5, 5))
        );

        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Data_Table.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Data_Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Data_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Data_TableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Data_Table);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Thêm Sinh Viên Mới");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Mã Sinh Viên:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Giới tính:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Họ và tên:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Ngày sinh:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Số điện thoại:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Email:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Lớp:");

        JName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jDate.setDateFormatString("dd/MM/yyyy");

        Gender.add(Nam);
        Nam.setText("Nam");

        Gender.add(Nu);
        Nu.setText("Nữ");

        Gender.add(Unisex);
        Unisex.setText("Chưa XĐ");

        JClass_Student.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        JClass_Student.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Refesh.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Refesh.setText("Nhập lại");
        Refesh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Refesh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Refesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefeshActionPerformed(evt);
            }
        });

        add.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Add-icon.png"))); // NOI18N
        add.setText("Thêm mới");
        add.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(Refesh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Refesh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JRoll.setEditable(false);
        JRoll.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        JEmail.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        JAdress.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Update_Mark_Student.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Update_Mark_Student.setText("Cập Nhập Điểm Môn Cho Tất Cả Sinh Viên");
        Update_Mark_Student.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Update_Mark_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_Mark_StudentActionPerformed(evt);
            }
        });

        JPhone.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(Nam)
                                .addGap(18, 18, 18)
                                .addComponent(Nu)
                                .addGap(18, 18, 18)
                                .addComponent(Unisex)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(JClass_Student, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JRoll)
                            .addComponent(JEmail)
                            .addComponent(JAdress, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JPhone)))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Update_Mark_Student, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JRoll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nam)
                    .addComponent(Nu)
                    .addComponent(Unisex))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JClass_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Update_Mark_Student, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDesktopPane.setLayer(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 972, Short.MAX_VALUE)
            .addGroup(jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
            .addGroup(jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPaneLayout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewActionPerformed
        int num = Data_Table.getSelectedRow();
        tbl_Student st = LS.get(num);
        View_Info_Student vis = new View_Info_Student(conn, st);
        jDesktopPane.add(vis);
        vis.setVisible(true);
    }//GEN-LAST:event_ViewActionPerformed

    private void RefeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefeshActionPerformed
        JName.setText("");
        JRoll.setText("");
        JPhone.setText("");
        JEmail.setText("");
        Nam.doClick();
        try {
            jDate.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2000/01/01"));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_User.class.getName()).log(Level.SEVERE, null, ex);
        }
        JAdress.setText("");
        JClass_Student.setSelectedIndex(0);
    }//GEN-LAST:event_RefeshActionPerformed

    private void Data_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Data_TableMousePressed
        jPopupMenu1.show(Data_Table, evt.getX(), evt.getY());
    }//GEN-LAST:event_Data_TableMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Seach.setText("");
        Class_Seach.setSelectedIndex(0);
        getStudent(" where Class_ID in (select ID from tbl_Class where Teacher_ID = " + acc.getID() + " )");
        loadTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Button_SeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SeachActionPerformed
        String search = Seach.getText();
        int select_cl = Class_Seach.getSelectedIndex() - 1;
        if (select_cl >= 0) {
            int id_class = LC.get(select_cl).getId();
            getStudent(" where Name like N'%" + search + "%' AND Class_ID = " + id_class);
        } else {
            getStudent(" where Name like N'%" + search + "%'");
        }
        loadTable();
    }//GEN-LAST:event_Button_SeachActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        int number_edit = Data_Table.getSelectedRow();
        tbl_Student student_edit = LS.get(number_edit);
        Edit_Student es = new Edit_Student(conn, student_edit, acc.getID(), acc.getRole_ID(), this);
        jDesktopPane.add(es);
        es.setVisible(true);
    }//GEN-LAST:event_EditActionPerformed

    private void Show_markActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Show_markActionPerformed
        int number_view_mark = Data_Table.getSelectedRow();
        tbl_Student student_view_mark = LS.get(number_view_mark);
        View_Mark vm = new View_Mark(conn, student_view_mark);
        jDesktopPane.add(vm);
        vm.setVisible(true);
    }//GEN-LAST:event_Show_markActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        String regex_Phone = "^0[0-9]{9,10}$";
        String Roll_New_Student = JRoll.getText();
        String Name_New_Student = JName.getText();
        String Phone_New_Student = JPhone.getText();
        String Email_New_Student = JEmail.getText();
        String Addrees_New_Student = JAdress.getText();
        String DOB_New_Student = String.valueOf(new SimpleDateFormat("yyyy/MM/dd").format(jDate.getDate()));
        int gender_New_Student = 1;
        if (Nam.isSelected()) {
            gender_New_Student = 1;
        } else if (Nu.isSelected()) {
            gender_New_Student = 2;
        } else if (Unisex.isSelected()) {
            gender_New_Student = 3;
        }
        int Class_ID_New_Student = LC.get(JClass_Student.getSelectedIndex()).getId();
        if (Name_New_Student.length() == 0 || Phone_New_Student.length() == 0 || Email_New_Student.length() == 0 || Addrees_New_Student.length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin vào các trường để tiến hành thêm mới");
        } else if (Phone_New_Student.length() > 11 || Phone_New_Student.length() < 10 || regex_Phone.matches(Phone_New_Student)) {
            JOptionPane.showMessageDialog(null, "Vui lòng điển đúng số điện thoại để tiếp tục!");
        } else if (check_Mail_Phone(Email_New_Student, Phone_New_Student)) {
            JOptionPane.showMessageDialog(null, "Email hoặc Số điện thoại đã được sử dụng!");
        } else {
            tbl_Student New_Student = new tbl_Student(Roll_New_Student, Name_New_Student, Phone_New_Student,
                    Email_New_Student, Addrees_New_Student, DOB_New_Student, 1, Class_ID_New_Student, "", gender_New_Student);
            Student_Controller SC = new Student_Controller(conn);
            if (SC.insert(New_Student) == 1) {
                JOptionPane.showMessageDialog(null, "Bạn đã thêm học sinh mới thành công");
                if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
                    getStudent("");
                } else {
                    getStudent(" where Class_ID in (select ID from tbl_Class where Teacher_ID = " + acc.getID() + " )");
                }
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra, vui lòng kiểm tra lại");
            }
        }
    }//GEN-LAST:event_addActionPerformed

    private void Update_Mark_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_Mark_StudentActionPerformed
        List<tbl_Subject> LSub;
        Subject_Controller SubCon = new Subject_Controller(conn);
        LSub = SubCon.select("");
        boolean check_insert = false;
        Mark_Controller markControll = new Mark_Controller(conn);
        for (tbl_Student st : LS) {
            for (tbl_Subject sub : LSub) {
                if (sub.getStatus() == 1) {
                    tbl_Mark mark = new tbl_Mark(st.getId(), sub.getID(), 0, 3, "");
                    if (markControll.insert(mark) == 1) {
                        check_insert = true;
                    }
                }
            }
        }
        if (check_insert) {
            JOptionPane.showMessageDialog(null, "Bạn đã cập nhập danh sách điểm thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Hệ Thống xảy ra lỗi, vui lòng kiểm tra lại");
        }
    }//GEN-LAST:event_Update_Mark_StudentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Seach;
    private javax.swing.JComboBox<String> Class_Seach;
    private javax.swing.JTable Data_Table;
    private javax.swing.JMenuItem Edit;
    private javax.swing.ButtonGroup Gender;
    private javax.swing.JTextField JAdress;
    private javax.swing.JComboBox<String> JClass_Student;
    private javax.swing.JTextField JEmail;
    private javax.swing.JTextField JName;
    private javax.swing.JTextField JPhone;
    private javax.swing.JTextField JRoll;
    private javax.swing.JRadioButton Nam;
    private javax.swing.JRadioButton Nu;
    private javax.swing.JButton Refesh;
    private javax.swing.JTextField Seach;
    private javax.swing.JMenuItem Show_mark;
    private javax.swing.JRadioButton Unisex;
    private javax.swing.JButton Update_Mark_Student;
    private javax.swing.JMenuItem View;
    private javax.swing.JButton add;
    private javax.swing.JLabel admin;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void load_table() {
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            getStudent("");
        } else {
            getStudent(" where Class_ID in (select ID from tbl_Class where Teacher_ID = " + acc.getID() + " )");
        }
        loadTable();
    }
}
