/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.panel;

import App.Internal.View_Class_Student;
import App.Internal.View_Class_Student.View_Mark_Student;
import App.Internal.View_Mark;
import App.controller.Class_Controller;
import App.controller.Course_Controller;
import App.controller.Student_Controller;
import App.controller.Subject_Controller;
import App.controller.Teacher_Controller;
import App.model.tbl_Class;
import App.model.tbl_Course;
import App.model.tbl_Student;
import App.model.tbl_Subject;
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
 * @author Hai Anh PC
 */
public class Panel_Subject extends javax.swing.JPanel implements View_Mark_Student {

    /**
     * Creates new form Panel_Subject
     */
    tbl_Subject edit;
    tbl_Course course_Edit;
    tbl_Class edit_class;
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Panel_Subject(Connection conn, tbl_Teacher acc) {
        this.conn = conn;
        this.acc = acc;
        initComponents();
        load_Subject_Table("");
        load_Course_Table("");
        load_Teach();
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            load_Class_Table("");
        } else {
            load_Class_Table(" WHERE Teacher_ID = " + acc.getID());
        }
        set_Cbx_Subject();
        set_Cbx_Class();
        try {
            BeginDate.setDate(sdf.parse("2020-01-01"));
            End_Date.setDate(sdf.parse("2020-01-01"));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
        set_Cbx_Class_Teacher();
        Panel_Edit_Subject.setVisible(false);
        panel_status.setVisible(false);
        Save_Edit_Course.setVisible(false);
        End_Edit_Course.setVisible(false);
        Save_Edit_Clas.setVisible(false);
        End_Edit_Clas.setVisible(false);
    }

    Connection conn;
    tbl_Teacher acc;

    List<tbl_Teacher> LT;
    List<tbl_Subject> LS;
    List<tbl_Class> LC;
    List<tbl_Course> LCA;
    List<tbl_Course> LCC;

    private void load_Teach() {
        Teacher_Controller TC = new Teacher_Controller(conn);
        LT = TC.select(" WHERE Role_ID < 4");
    }

    private void load_Subject_Table(String check) {
        Subject_Controller SC = new Subject_Controller(conn);
        LS = SC.select(check);
        DefaultTableModel DTM = new DefaultTableModel();
        DTM.addColumn("STT");
        DTM.addColumn("Tên môn");
        DTM.addColumn("Số tín");
        DTM.addColumn("Học phí");
        DTM.addColumn("Trạng thái");
        for (int i = 0; i < LS.size(); i++) {
            tbl_Subject s = LS.get(i);
            if (s.getStatus() == 1) {
                Object o[] = {
                    (i + 1), s.getName(), s.getAccredit(), s.getPrice(), "Đang giảng dạy"
                };
                DTM.addRow(o);
            } else {
                Object o[] = {
                    (i + 1), s.getName(), s.getAccredit(), s.getPrice(), "Đã ngưng giảng dạy"
                };
                DTM.addRow(o);
            }
        }
        Subject_Table.setModel(DTM);
        Subject_Table.setRowHeight(25);
    }

    private void load_Class_Table(String check) {
        Class_Controller CC = new Class_Controller(conn);
        if ((acc.getRole_ID() == 1 || acc.getRole_ID() == 2) && "".equals(check)) {
            LC = CC.select(check);
        } else {
            LC = CC.select(check);
        }
        DefaultTableModel DTM = new DefaultTableModel();
        DTM.addColumn("STT");
        DTM.addColumn("Tên lớp");
        DTM.addColumn("Khóa");
        DTM.addColumn("Số học sinh");
        DTM.addColumn("Giáo viên chủ nhiệm");
        Student_Controller SC = new Student_Controller(conn);
        for (int i = 0; i < LC.size(); i++) {
            tbl_Class c = LC.get(i);
            for (tbl_Teacher t : LT) {
                if (t.getID() == c.getTeacher_id()) {
                    for (tbl_Course course : LCA) {
                        if (course.getId() == c.getCourse_id()) {
                            Object o[] = {
                                (i + 1), c.getName(), course.getName(), SC.select(" WHERE Class_ID = " + c.getId()).size(), t.getName()
                            };
                            DTM.addRow(o);
                        }
                    }
                }
            }
        }
        Class_Table.setModel(DTM);
        Class_Table.setRowHeight(25);
    }

    private void load_Course_Table(String check) {
        Course_Controller CC = new Course_Controller(conn);
        Class_Controller Class_C = new Class_Controller(conn);
        LCA = CC.select("");
        LCC = CC.select(check);
        DefaultTableModel DTM = new DefaultTableModel();
        DTM.addColumn("STT");
        DTM.addColumn("Tên");
        DTM.addColumn("Số lớp");
        DTM.addColumn("Khai giảng");
        DTM.addColumn("Kết thúc");
        for (int i = 0; i < LCC.size(); i++) {
            tbl_Course c = LCC.get(i);
            Object o[] = {
                (i + 1), c.getName(), Class_C.select(" WHERE Course_ID = " + c.getId()).size(), c.getBegin_date(), c.getEnd_date()
            };
            DTM.addRow(o);
        }
        Course_Table.setModel(DTM);
        Course_Table.setRowHeight(25);
    }

    private void set_Cbx_Subject() {
        DefaultComboBoxModel DCM = new DefaultComboBoxModel();
        DCM.addElement("Đang giảng dạy");
        DCM.addElement("Đã ngưng giảng dạy");
        Cbx_Subject.setModel(DCM);
    }

    private void set_Cbx_Class() {
        DefaultComboBoxModel DCM = new DefaultComboBoxModel();
        for (tbl_Course c : LCA) {
            DCM.addElement(c.getName());
        }
        Cbx_Class.setModel(DCM);
        Cbx_Class_Course.setModel(DCM);
    }

    private void set_Cbx_Class_Teacher() {
        DefaultComboBoxModel DCM = new DefaultComboBoxModel();
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            for (tbl_Teacher t : LT) {
                DCM.addElement(t.getName());
            }
        } else {
            DCM.addElement(acc.getName());
        }
        Cbx_Class_Teacher.setModel(DCM);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Subject_Popup = new javax.swing.JPopupMenu();
        Subject_Edit = new javax.swing.JMenuItem();
        Course_Popup = new javax.swing.JPopupMenu();
        Course_Edit = new javax.swing.JMenuItem();
        Class_PopUp = new javax.swing.JPopupMenu();
        Class_Edit = new javax.swing.JMenuItem();
        Class_Student = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Subject_Search = new javax.swing.JTextField();
        Cbx_Subject = new javax.swing.JComboBox<>();
        Search_Subject = new javax.swing.JButton();
        Reload_Subject = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Subject_Table = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Sub_Name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Accredit = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        Price = new javax.swing.JFormattedTextField();
        panel_status = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        now = new javax.swing.JRadioButton();
        unnow = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        Panel_Add_Subject = new javax.swing.JPanel();
        Add_Subject = new javax.swing.JButton();
        Refesh_Subject = new javax.swing.JButton();
        Panel_Edit_Subject = new javax.swing.JPanel();
        Save_Edit = new javax.swing.JButton();
        End_Edit = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        Seach_Course = new javax.swing.JTextField();
        Search_Course = new javax.swing.JButton();
        Reload_Course = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Course_Table = new javax.swing.JTable();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        Name_Course = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        BeginDate = new com.toedter.calendar.JDateChooser();
        End_Date = new com.toedter.calendar.JDateChooser();
        jPanel34 = new javax.swing.JPanel();
        Add_Course = new javax.swing.JButton();
        Refesh_Course = new javax.swing.JButton();
        Save_Edit_Course = new javax.swing.JButton();
        End_Edit_Course = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        Class_Search = new javax.swing.JTextField();
        Cbx_Class = new javax.swing.JComboBox<>();
        Search_Class = new javax.swing.JButton();
        Reload_Class = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Class_Table = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        Name_Class = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Cbx_Class_Course = new javax.swing.JComboBox<>();
        Cbx_Class_Teacher = new javax.swing.JComboBox<>();
        jPanel27 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        End_Edit_Clas = new javax.swing.JButton();
        Save_Edit_Clas = new javax.swing.JButton();

        Subject_Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Subject_Edit.setText("Sửa thông tin môn học");
        Subject_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Subject_EditActionPerformed(evt);
            }
        });
        Subject_Popup.add(Subject_Edit);

        Course_Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Course_Edit.setText("Sửa thông tin");
        Course_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Course_EditActionPerformed(evt);
            }
        });
        Course_Popup.add(Course_Edit);

        Class_Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Class_Edit.setText("Sửa thông tin lớp");
        Class_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Class_EditActionPerformed(evt);
            }
        });
        Class_PopUp.add(Class_Edit);

        Class_Student.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Class_Student.setText("Danh sách sinh viên");
        Class_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Class_StudentActionPerformed(evt);
            }
        });
        Class_PopUp.add(Class_Student);

        jDesktopPane1.setBackground(new java.awt.Color(240, 240, 240));

        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Tên Môn:");

        Subject_Search.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Cbx_Subject.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Cbx_Subject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Search_Subject.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Search_Subject.setText("Tìm kiếm");
        Search_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_SubjectActionPerformed(evt);
            }
        });

        Reload_Subject.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Reload_Subject.setText("Tải lại D.Sách");
        Reload_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reload_SubjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Subject_Search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cbx_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Search_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Reload_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Search_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cbx_Subject)
                    .addComponent(Subject_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reload_Subject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Subject_Table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Subject_Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Subject_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Subject_TableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Subject_Table);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Môn Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Tên Môn Học:");

        Sub_Name.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Sub_Name.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Số Tín:");

        Accredit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel6.setText("Học Phí:");

        Price.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.0"))));
        Price.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Price.setText("0");
        Price.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("Trạng thái:");

        buttonGroup1.add(now);
        now.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        now.setText("Đang giảng dạy");
        now.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nowActionPerformed(evt);
            }
        });

        buttonGroup1.add(unnow);
        unnow.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        unnow.setText("Đã Ngưng giảng dạy");

        javax.swing.GroupLayout panel_statusLayout = new javax.swing.GroupLayout(panel_status);
        panel_status.setLayout(panel_statusLayout);
        panel_statusLayout.setHorizontalGroup(
            panel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_statusLayout.createSequentialGroup()
                .addGroup(panel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_statusLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(now, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(unnow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panel_statusLayout.setVerticalGroup(
            panel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_statusLayout.createSequentialGroup()
                .addGroup(panel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(now, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unnow)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sub_Name, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Accredit, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(Sub_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(Accredit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                .addComponent(panel_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Add_Subject.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Add_Subject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Add-icon.png"))); // NOI18N
        Add_Subject.setText("Thêm môn");
        Add_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_SubjectActionPerformed(evt);
            }
        });

        Refesh_Subject.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Refesh_Subject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Reset-icon.png"))); // NOI18N
        Refesh_Subject.setText("Làm Lại");
        Refesh_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refesh_SubjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_Add_SubjectLayout = new javax.swing.GroupLayout(Panel_Add_Subject);
        Panel_Add_Subject.setLayout(Panel_Add_SubjectLayout);
        Panel_Add_SubjectLayout.setHorizontalGroup(
            Panel_Add_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Add_SubjectLayout.createSequentialGroup()
                .addComponent(Refesh_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Add_Subject))
        );
        Panel_Add_SubjectLayout.setVerticalGroup(
            Panel_Add_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Add_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Add_Subject)
                .addComponent(Refesh_Subject))
        );

        Save_Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Save_Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/edit.png"))); // NOI18N
        Save_Edit.setText("Lưu lại");
        Save_Edit.setToolTipText("");
        Save_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_EditActionPerformed(evt);
            }
        });

        End_Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        End_Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Close-icon.png"))); // NOI18N
        End_Edit.setText("Hủy bỏ");
        End_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                End_EditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_Edit_SubjectLayout = new javax.swing.GroupLayout(Panel_Edit_Subject);
        Panel_Edit_Subject.setLayout(Panel_Edit_SubjectLayout);
        Panel_Edit_SubjectLayout.setHorizontalGroup(
            Panel_Edit_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Edit_SubjectLayout.createSequentialGroup()
                .addComponent(End_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Save_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Panel_Edit_SubjectLayout.setVerticalGroup(
            Panel_Edit_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Edit_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Save_Edit)
                .addComponent(End_Edit))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_Add_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Panel_Edit_Subject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Add_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_Edit_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Môn học", jPanel3);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel15.setText("Tên Khóa:");

        Seach_Course.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Search_Course.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Search_Course.setText("Tìm kiếm");
        Search_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_CourseActionPerformed(evt);
            }
        });

        Reload_Course.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Reload_Course.setText("Tải lại D.Sách");
        Reload_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reload_CourseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Seach_Course, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Search_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Reload_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Search_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Seach_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reload_Course, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Course_Table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Course_Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Course_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Course_TableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(Course_Table);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Khóa Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel16.setText("Tên Khóa Học:");

        Name_Course.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Name_Course.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel17.setText("Ngày bắt đầu khóa:");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel18.setText("Ngày kết thúc khóa:");

        BeginDate.setDateFormatString("dd/MM/yyyy");
        BeginDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        End_Date.setDateFormatString("dd/MM/yyyy");
        End_Date.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Name_Course)
                    .addComponent(BeginDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(End_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel16)
                .addGap(10, 10, 10)
                .addComponent(Name_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel17)
                .addGap(10, 10, 10)
                .addComponent(BeginDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel18)
                .addGap(10, 10, 10)
                .addComponent(End_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Add_Course.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Add_Course.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Add-icon.png"))); // NOI18N
        Add_Course.setText("Thêm mới");
        Add_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_CourseActionPerformed(evt);
            }
        });

        Refesh_Course.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Refesh_Course.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Reset-icon.png"))); // NOI18N
        Refesh_Course.setText("Làm Lại");
        Refesh_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refesh_CourseActionPerformed(evt);
            }
        });

        Save_Edit_Course.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Save_Edit_Course.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/edit.png"))); // NOI18N
        Save_Edit_Course.setText("Lưu lại");
        Save_Edit_Course.setToolTipText("");
        Save_Edit_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_Edit_CourseActionPerformed(evt);
            }
        });

        End_Edit_Course.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        End_Edit_Course.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Close-icon.png"))); // NOI18N
        End_Edit_Course.setText("Hủy bỏ");
        End_Edit_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                End_Edit_CourseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addComponent(End_Edit_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Save_Edit_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addComponent(Refesh_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Add_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Refesh_Course)
                    .addComponent(Add_Course, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save_Edit_Course)
                    .addComponent(End_Edit_Course))
                .addContainerGap(201, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Khóa học", jPanel7);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel11.setText("Tên Lớp:");

        Class_Search.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Cbx_Class.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Cbx_Class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Cbx_Class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cbx_ClassActionPerformed(evt);
            }
        });

        Search_Class.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Search_Class.setText("Tìm kiếm");
        Search_Class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_ClassActionPerformed(evt);
            }
        });

        Reload_Class.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Reload_Class.setText("Tải lại D.Sách");
        Reload_Class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reload_ClassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Class_Search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cbx_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Search_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Reload_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Search_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cbx_Class)
                    .addComponent(Class_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reload_Class, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Class_Table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Class_Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Class_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Class_TableMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(Class_Table);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Lớp Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel12.setText("Tên Lớp Học:");

        Name_Class.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Name_Class.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel13.setText("Khóa học:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel14.setText("Giáo viên chủ nhiệm:");

        Cbx_Class_Course.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Cbx_Class_Course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Cbx_Class_Teacher.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Cbx_Class_Teacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Name_Class)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Cbx_Class_Course, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Cbx_Class_Teacher, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addGap(10, 10, 10)
                .addComponent(Name_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cbx_Class_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(Cbx_Class_Teacher, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton11.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Add-icon.png"))); // NOI18N
        jButton11.setText("Thêm lớp");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Reset-icon.png"))); // NOI18N
        jButton12.setText("Làm Lại");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        End_Edit_Clas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        End_Edit_Clas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Close-icon.png"))); // NOI18N
        End_Edit_Clas.setText("Hủy bỏ");
        End_Edit_Clas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                End_Edit_ClasActionPerformed(evt);
            }
        });

        Save_Edit_Clas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Save_Edit_Clas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/edit.png"))); // NOI18N
        Save_Edit_Clas.setText("Lưu lại");
        Save_Edit_Clas.setToolTipText("");
        Save_Edit_Clas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_Edit_ClasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(End_Edit_Clas, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Save_Edit_Clas, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton12)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save_Edit_Clas)
                    .addComponent(End_Edit_Clas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lớp Học", jPanel14);

        jDesktopPane1.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void Cbx_ClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cbx_ClassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cbx_ClassActionPerformed

    private void Reload_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reload_SubjectActionPerformed
        load_Subject_Table("");
    }//GEN-LAST:event_Reload_SubjectActionPerformed

    private void Reload_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reload_CourseActionPerformed
        load_Course_Table("");
    }//GEN-LAST:event_Reload_CourseActionPerformed

    private void Reload_ClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reload_ClassActionPerformed
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            load_Class_Table("");
        } else {
            load_Class_Table(" WHERE Teacher_ID = " + acc.getID());
        }
    }//GEN-LAST:event_Reload_ClassActionPerformed

    private void Search_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_SubjectActionPerformed
        String seach = Subject_Search.getText();
        int sta = Cbx_Subject.getSelectedIndex() + 1;
        load_Subject_Table(" WHERE Name LIKE N'%" + seach + "%'" + " AND status = " + sta);
    }//GEN-LAST:event_Search_SubjectActionPerformed

    private void Search_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_CourseActionPerformed
        String search = Seach_Course.getText();
        load_Course_Table(" WHERE Name LIKE N'%" + search + "%'");
    }//GEN-LAST:event_Search_CourseActionPerformed

    private void Search_ClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_ClassActionPerformed
        String seachr = Class_Search.getText();
        int course_id = LCA.get(Cbx_Class.getSelectedIndex()).getId();
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            load_Class_Table("WHERE Name LIKE N'%" + seachr + "%' AND Course_ID = " + course_id);
        } else {
            load_Class_Table("WHERE Name LIKE N'%" + seachr + "%' AND Course_ID = " + course_id + " AND Teacher_ID = " + acc.getID());
        }
    }//GEN-LAST:event_Search_ClassActionPerformed

    private void Refesh_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refesh_SubjectActionPerformed
        Sub_Name.setText("");
        Accredit.setValue(0);
        Price.setText("0");
    }//GEN-LAST:event_Refesh_SubjectActionPerformed

    private void Refesh_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refesh_CourseActionPerformed
        Name_Course.setText("");
        try {
            BeginDate.setDate(sdf.parse("2020-01-01"));
            End_Date.setDate(sdf.parse("2020-01-01"));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Refesh_CourseActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Name_Class.setText("");
        Cbx_Class_Course.setSelectedIndex(0);
        Cbx_Class_Teacher.setSelectedIndex(0);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void Add_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_SubjectActionPerformed
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            String name = Sub_Name.getText();
            int accredit = Integer.parseInt(String.valueOf(Accredit.getValue()));
            float price = Float.parseFloat(Price.getText());
            if (!"".equals(name)) {
                tbl_Subject subject = new tbl_Subject(name, accredit, price, 1);
                Subject_Controller SC = new Subject_Controller(conn);
                if (SC.insert(subject) == 1) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    Sub_Name.setText("");
                    Accredit.setValue(0);
                    Price.setText("0");
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi rồi!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tên đang trống");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn không đủ quyền hạn để thực hiện");
        }
        load_Subject_Table("");
    }//GEN-LAST:event_Add_SubjectActionPerformed

    private void Add_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_CourseActionPerformed
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            String name = Name_Course.getText();
            String begindate = sdf.format(BeginDate.getDate());
            String enddate = sdf.format(End_Date.getDate());
            if (!"".equals(name)) {
                tbl_Course new_course = new tbl_Course(name, begindate, enddate);
                Course_Controller CC = new Course_Controller(conn);
                if (CC.insert(new_course) == 1) {
                    JOptionPane.showMessageDialog(null, "Bạn đã thêm thành công");
                    Name_Course.setText("");
                    try {
                        BeginDate.setDate(sdf.parse("2020-01-01"));
                        End_Date.setDate(sdf.parse("2020-01-01"));
                    } catch (ParseException ex) {
                        Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi rồi");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tên đang trống");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn không đủ quyền hạn để thực hiện");
        }
        load_Course_Table("");
    }//GEN-LAST:event_Add_CourseActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        tbl_Class new_C;
        if (acc.getRole_ID() > 3) {
            JOptionPane.showMessageDialog(null, "Bạn không đủ quyền hạn để thực hiện");
        } else {
            if (acc.getRole_ID() == 2) {
                String name = Name_Class.getText();
                int Course_ID = LCA.get(Cbx_Class_Course.getSelectedIndex()).getId();
                new_C = new tbl_Class(name, Course_ID, acc.getID());
            } else {
                String name = Name_Class.getText();
                int Course_ID = LCA.get(Cbx_Class_Course.getSelectedIndex()).getId();
                int Teach_ID = LT.get(Cbx_Class_Teacher.getSelectedIndex()).getID();
                new_C = new tbl_Class(name, Course_ID, Teach_ID);
            }
            if (!"".equals(new_C.getName())) {
                Class_Controller CC = new Class_Controller(conn);
                if (CC.insert(new_C) == 1) {
                    JOptionPane.showMessageDialog(null, "Thành công");
                    Name_Class.setText("");
                    Cbx_Class_Course.setSelectedIndex(0);
                    Cbx_Class_Teacher.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi rồi");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tên đang trống");
            }
        }
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            load_Class_Table("");
        } else {
            load_Class_Table(" WHERE Teacher_ID = " + acc.getID());
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void Save_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_EditActionPerformed
        String name = (Sub_Name.getText());
        int accredit = Integer.parseInt(String.valueOf(Accredit.getValue()));
        float price = Float.parseFloat(Price.getText());
        if (now.isSelected()) {
            edit.setStatus(1);
        } else {
            edit.setStatus(2);
        }
        if (accredit < 1) {
            JOptionPane.showMessageDialog(null, "Số tín không thể bằng nhỏ hơn 1");
        } else if (price < 1) {
            JOptionPane.showMessageDialog(null, "Học phí thấp thế thì ăn cám à?");
        } else if ("".equals(name)) {
            JOptionPane.showMessageDialog(null, "Làm j có môn nào tên là ''?");
        } else {
            edit.setName(name);
            edit.setAccredit(accredit);
            edit.setPrice(price);
            Subject_Controller SC = new Subject_Controller(conn);
            if (SC.update(edit)) {
                JOptionPane.showMessageDialog(null, "Bạn đã cập nhập dữ liệu thành công");
                load_Subject_Table("");
                Panel_Add_Subject.setVisible(true);
                Panel_Edit_Subject.setVisible(false);
                jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Môn Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
                Sub_Name.setText("");
                Accredit.setValue(0);
                Price.setText("0");
                panel_status.setVisible(false);
                edit = null;
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi rồi!");
            }
        }
    }//GEN-LAST:event_Save_EditActionPerformed

    private void End_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_End_EditActionPerformed
        Panel_Add_Subject.setVisible(true);
        Panel_Edit_Subject.setVisible(false);
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Môn Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        Sub_Name.setText("");
        Accredit.setValue(0);
        Price.setText("0");
        panel_status.setVisible(false);
        edit = null;
    }//GEN-LAST:event_End_EditActionPerformed

    private void Subject_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subject_TableMousePressed
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            Subject_Popup.show(Subject_Table, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_Subject_TableMousePressed

    private void Subject_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Subject_EditActionPerformed
        Panel_Add_Subject.setVisible(false);
        Panel_Edit_Subject.setVisible(true);
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sửa Thông Tin Môn Học", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        edit = LS.get(Subject_Table.getSelectedRow());
        Sub_Name.setText(edit.getName());
        Accredit.setValue(edit.getAccredit());
        Price.setText(String.valueOf(edit.getPrice()));
        if (edit.getStatus() == 1) {
            now.setSelected(true);
        } else {
            unnow.setSelected(true);
        }
        panel_status.setVisible(true);
    }//GEN-LAST:event_Subject_EditActionPerformed

    private void nowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nowActionPerformed

    private void Course_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Course_TableMousePressed
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            Course_Popup.show(Course_Table, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_Course_TableMousePressed

    private void Class_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Class_TableMousePressed
        Class_PopUp.show(Class_Table, evt.getX(), evt.getY());
    }//GEN-LAST:event_Class_TableMousePressed

    private void Save_Edit_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_Edit_CourseActionPerformed
        String name_course = Name_Course.getText();
        String begin_date = sdf.format(BeginDate.getDate());
        String end_date = sdf.format(End_Date.getDate());
        if ("".equals(name_course)) {
            JOptionPane.showMessageDialog(null, "Tên khóa học đang để trống");
        } else {
            course_Edit.setName(name_course);
            course_Edit.setBegin_date(begin_date);
            course_Edit.setEnd_date(end_date);
            Course_Controller CC = new Course_Controller(conn);
            if (CC.update(course_Edit)) {
                JOptionPane.showMessageDialog(null, "Đã Cập nhập thông tin thành công");
                course_Edit = null;
                jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm khóa học mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
                Refesh_Course.setVisible(true);
                Add_Course.setVisible(true);
                End_Edit_Course.setVisible(false);
                Save_Edit_Course.setVisible(false);
                load_Course_Table("");
                Name_Course.setText("");
                try {
                    BeginDate.setDate(sdf.parse("2020-01-01"));
                    End_Date.setDate(sdf.parse("2020-01-01"));
                } catch (ParseException ex) {
                    Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi rồi");
            }
        }
    }//GEN-LAST:event_Save_Edit_CourseActionPerformed

    private void End_Edit_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_End_Edit_CourseActionPerformed
        course_Edit = null;
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm khóa học mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        Refesh_Course.setVisible(true);
        Add_Course.setVisible(true);
        End_Edit_Course.setVisible(false);
        Save_Edit_Course.setVisible(false);
        Name_Course.setText("");
        try {
            BeginDate.setDate(sdf.parse("2020-01-01"));
            End_Date.setDate(sdf.parse("2020-01-01"));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_End_Edit_CourseActionPerformed

    private void End_Edit_ClasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_End_Edit_ClasActionPerformed
        edit_class = null;
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Lớp Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        Name_Class.setText("");
        Cbx_Class_Course.setSelectedIndex(0);
        Cbx_Class_Teacher.setSelectedIndex(0);
        Save_Edit_Clas.setVisible(false);
        End_Edit_Clas.setVisible(false);
        jButton11.setVisible(true);
        jButton12.setVisible(true);
    }//GEN-LAST:event_End_Edit_ClasActionPerformed

    private void Save_Edit_ClasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_Edit_ClasActionPerformed
        String name_Class_edit = Name_Class.getText();
        int Course_ID_Class_Edit = LCA.get(Cbx_Class_Course.getSelectedIndex()).getId();
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            int Teacher_ID_Class_Edit = LT.get(Cbx_Class_Teacher.getSelectedIndex()).getID();
            edit_class.setTeacher_id(Teacher_ID_Class_Edit);
        }
        edit_class.setCourse_id(Course_ID_Class_Edit);
        if ("".equals(name_Class_edit)) {
            JOptionPane.showMessageDialog(null, "Tên lớp đang để trống");
        } else {
            edit_class.setName(name_Class_edit);
            Class_Controller CC = new Class_Controller(conn);
            if (CC.update(edit_class)) {
                JOptionPane.showMessageDialog(null, "Cập nhập dữ liệu thành công");
                edit_class = null;
                jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Lớp Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
                Name_Class.setText("");
                Cbx_Class_Course.setSelectedIndex(0);
                if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
                    Cbx_Class_Teacher.setSelectedIndex(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi rồi!");
            }
        }
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            load_Class_Table("");
        } else {
            load_Class_Table(" WHERE Teacher_ID = " + acc.getID());
        }
    }//GEN-LAST:event_Save_Edit_ClasActionPerformed

    private void Course_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Course_EditActionPerformed
        course_Edit = LCC.get(Course_Table.getSelectedRow());
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin khóa học", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        Refesh_Course.setVisible(false);
        Add_Course.setVisible(false);
        End_Edit_Course.setVisible(true);
        Save_Edit_Course.setVisible(true);
        Name_Course.setText(course_Edit.getName());
        try {
            BeginDate.setDate(sdf.parse(course_Edit.getBegin_date()));
            End_Date.setDate(sdf.parse(course_Edit.getEnd_date()));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Course_EditActionPerformed

    private void Class_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Class_EditActionPerformed
        edit_class = LC.get(Class_Table.getSelectedRow());
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông Tin Lớp Học", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        Name_Class.setText(edit_class.getName());
        for (int i = 0; i < LCA.size(); i++) {
            tbl_Course c = LCA.get(i);
            if (c.getId() == edit_class.getCourse_id()) {
                Cbx_Class_Course.setSelectedIndex(i);
            }
        }
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            for (int i = 0; i < LT.size(); i++) {
                tbl_Teacher t = LT.get(i);
                if (t.getID() == edit_class.getTeacher_id()) {
                    Cbx_Class_Teacher.setSelectedIndex(i);
                }
            }
        }
        Save_Edit_Clas.setVisible(true);
        End_Edit_Clas.setVisible(true);
        jButton11.setVisible(false);
        jButton12.setVisible(false);
    }//GEN-LAST:event_Class_EditActionPerformed

    private void Class_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Class_StudentActionPerformed
        tbl_Class Class_View = LC.get(Class_Table.getSelectedRow());
        View_Class_Student VCS = new View_Class_Student(Class_View, conn, this);
        jDesktopPane1.add(VCS);
        VCS.setVisible(true);
    }//GEN-LAST:event_Class_StudentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner Accredit;
    private javax.swing.JButton Add_Course;
    private javax.swing.JButton Add_Subject;
    private com.toedter.calendar.JDateChooser BeginDate;
    private javax.swing.JComboBox<String> Cbx_Class;
    private javax.swing.JComboBox<String> Cbx_Class_Course;
    private javax.swing.JComboBox<String> Cbx_Class_Teacher;
    private javax.swing.JComboBox<String> Cbx_Subject;
    private javax.swing.JMenuItem Class_Edit;
    private javax.swing.JPopupMenu Class_PopUp;
    private javax.swing.JTextField Class_Search;
    private javax.swing.JMenuItem Class_Student;
    private javax.swing.JTable Class_Table;
    private javax.swing.JMenuItem Course_Edit;
    private javax.swing.JPopupMenu Course_Popup;
    private javax.swing.JTable Course_Table;
    private com.toedter.calendar.JDateChooser End_Date;
    private javax.swing.JButton End_Edit;
    private javax.swing.JButton End_Edit_Clas;
    private javax.swing.JButton End_Edit_Course;
    private javax.swing.JTextField Name_Class;
    private javax.swing.JTextField Name_Course;
    private javax.swing.JPanel Panel_Add_Subject;
    private javax.swing.JPanel Panel_Edit_Subject;
    private javax.swing.JFormattedTextField Price;
    private javax.swing.JButton Refesh_Course;
    private javax.swing.JButton Refesh_Subject;
    private javax.swing.JButton Reload_Class;
    private javax.swing.JButton Reload_Course;
    private javax.swing.JButton Reload_Subject;
    private javax.swing.JButton Save_Edit;
    private javax.swing.JButton Save_Edit_Clas;
    private javax.swing.JButton Save_Edit_Course;
    private javax.swing.JTextField Seach_Course;
    private javax.swing.JButton Search_Class;
    private javax.swing.JButton Search_Course;
    private javax.swing.JButton Search_Subject;
    private javax.swing.JTextField Sub_Name;
    private javax.swing.JMenuItem Subject_Edit;
    private javax.swing.JPopupMenu Subject_Popup;
    private javax.swing.JTextField Subject_Search;
    private javax.swing.JTable Subject_Table;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton now;
    private javax.swing.JPanel panel_status;
    private javax.swing.JRadioButton unnow;
    // End of variables declaration//GEN-END:variables

    @Override
    public void viewMark(tbl_Student s) {
        View_Mark vm = new View_Mark(conn, s);
        jDesktopPane1.add(vm);
        vm.setVisible(true);
    }
}
