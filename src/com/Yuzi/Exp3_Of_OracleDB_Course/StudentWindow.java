package com.Yuzi.Exp3_Of_OracleDB_Course;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

class StudentWindow extends JFrame {
    private String SNO;
    private String SNAME;
    private String SSEX;
    private String SAGE;
    private JLabel jLabel_LoginUserName;
    private JTable jTable_init;
    private JTable jTable_grade;
    private JScrollPane jScrollPane;

    StudentWindow(String SNO) throws SQLException {
        this.SNO = SNO;
        ConnectDB connectDB = new ConnectDB();
        connectDB.DoSql("select * from student where SNO=" + SNO);
        connectDB.resultset.next();
        SNAME = connectDB.resultset.getString("SNAME");
        SSEX = connectDB.resultset.getString("SSEX");
        SAGE = connectDB.resultset.getString("SAGE");
        init();
        setBounds(50, 20, 900, 600);
        setTitle("学生界面");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() throws SQLException {
        setLayout(new BorderLayout());
        JLabel label1 = new JLabel("<html><body><br/><br/>欢迎使用学生课程系统！<br/><br/></body></html>", JLabel.CENTER);
        label1.setFont(new Font("华文新魏", 1, 25));
        add(label1, BorderLayout.NORTH);
        jLabel_LoginUserName = new JLabel("当前登录用户：" + SNAME);
        jLabel_LoginUserName.setFont(new Font("仿宋", 0, 15));
        add(jLabel_LoginUserName, BorderLayout.SOUTH);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu jMenu_Person = new JMenu("个人");
        jMenu_Person.setPreferredSize(new Dimension(45, 30));
        menuBar.add(jMenu_Person);
        JMenu jMenu_Courses = new JMenu("课程");
        jMenu_Courses.setPreferredSize(new Dimension(45, 30));
        menuBar.add(jMenu_Courses);
        JMenu jMenu_Statistics = new JMenu("统计");
        jMenu_Statistics.setPreferredSize(new Dimension(45, 30));
        menuBar.add(jMenu_Statistics);
        JMenuItem jMenuItem_Person_changepwd = new JMenuItem("更改密码");
        jMenuItem_Person_changepwd.setPreferredSize(new Dimension(120, 30));
        jMenu_Person.add(jMenuItem_Person_changepwd);
        JMenuItem jMenuItem_Person_changeperson = new JMenuItem("更改个人信息");
        jMenuItem_Person_changeperson.setPreferredSize(new Dimension(120, 30));
        jMenu_Person.add(jMenuItem_Person_changeperson);
        JMenuItem jMenuItem_Courses_add = new JMenuItem("添加课程");
        jMenuItem_Courses_add.setPreferredSize(new Dimension(120, 30));
        jMenu_Courses.add(jMenuItem_Courses_add);
        JMenuItem jMenuItem_Courses_delete = new JMenuItem("退选课程");
        jMenuItem_Courses_delete.setPreferredSize(new Dimension(120, 30));
        jMenu_Courses.add(jMenuItem_Courses_delete);
        JMenuItem jMenuItem_Statistics_grade = new JMenuItem("查询成绩");
        jMenuItem_Statistics_grade.setPreferredSize(new Dimension(120, 30));
        jMenu_Statistics.add(jMenuItem_Statistics_grade);
        JMenuItem jMenuItem_Statistics_credit = new JMenuItem("查询学分");
        jMenuItem_Statistics_credit.setPreferredSize(new Dimension(120, 30));
        jMenu_Statistics.add(jMenuItem_Statistics_credit);
        jMenuItem_Person_changepwd.addActionListener(e -> {
            JPasswordField password;
            JButton button;
            JPasswordField password_repeat;
            JFrame changePwd = new JFrame("修改密码");
            changePwd.setBounds(500, 200, 310, 260);
            changePwd.setResizable(false);
            changePwd.setLayout(null);
            JLabel jl1 = new JLabel("新密码：");
            jl1.setBounds(30, 50, 100, 30);
            changePwd.add(jl1);
            password = new JPasswordField(10);
            password.setBounds(80, 50, 150, 30);
            changePwd.add(password);
            password_repeat = new JPasswordField(10);
            JLabel jl2 = new JLabel("重复密码：");
            jl2.setBounds(20, 100, 100, 30);
            changePwd.add(jl2);
            password_repeat.setBounds(80, 100, 150, 30);
            changePwd.add(password_repeat);
            button = new JButton("确定");
            button.setBounds(115, 150, 70, 30);
            changePwd.add(button);
            button.addActionListener(e1 -> {
                if (Objects.equals(password.getText(), password_repeat.getText())) {
                    if (password.getPassword() != null) {
                        ConnectDB connectDB = new ConnectDB();
                        connectDB.DoSql("update student set PWD=" + password.getText() + " where SNO=" + SNO);
                        connectDB.DoSql("select * from student where SNO=" + SNO);
                        try {
                            connectDB.resultset.next();
                            if (!Objects.equals(connectDB.resultset.getString("PWD"), password.getText())) {
                                JOptionPane.showMessageDialog(null, "修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "修改成功！");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "两次输入的密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            });
            changePwd.setVisible(true);
        });//修改密码代码实现
        jMenuItem_Person_changeperson.addActionListener(e -> {
            JFrame change_Person = new JFrame("修改个人信息");
            change_Person.setBounds(500, 200, 310, 360);
            change_Person.setResizable(false);
            change_Person.setLayout(null);
            JLabel change_Person_sno = new JLabel("学号：    " + SNO);
            change_Person_sno.setBounds(30, 50, 100, 30);
            change_Person.add(change_Person_sno);
            JLabel jLabel_change_name = new JLabel("姓名：");
            jLabel_change_name.setBounds(30, 100, 100, 30);
            change_Person.add(jLabel_change_name);
            JTextField jTextField_Sname = new JTextField(SNAME);
            jTextField_Sname.setBounds(80, 100, 150, 30);
            change_Person.add(jTextField_Sname);
            JLabel jLabel_change_sex = new JLabel("性别：");
            jLabel_change_sex.setBounds(30, 150, 100, 30);
            change_Person.add(jLabel_change_sex);
            JTextField jTextField_Ssex = new JTextField(SSEX);
            jTextField_Ssex.setBounds(80, 150, 150, 30);
            change_Person.add(jTextField_Ssex);
            JLabel jLabel_change_age = new JLabel("年龄：");
            jLabel_change_age.setBounds(30, 200, 100, 30);
            change_Person.add(jLabel_change_age);
            JTextField jTextField_Sage = new JTextField(SAGE);
            jTextField_Sage.setBounds(80, 200, 150, 30);
            change_Person.add(jTextField_Sage);
            JButton button = new JButton("确定修改");
            button.setBounds(105, 250, 90, 30);
            button.addActionListener(e1 -> {
                ConnectDB connectDB = new ConnectDB();
                if (jTextField_Sage.getText().length() != 0 && jTextField_Sname.getText().length() != 0 && jTextField_Ssex.getText().length() != 0) {
                    connectDB.DoSql("update student set SNAME='" + jTextField_Sname.getText() + "',SAGE='" + jTextField_Sage.getText() +
                            "',SSEX='" + jTextField_Ssex.getText() + "' where SNO=" + SNO);
                    connectDB.DoSql("select * from student where SNO=" + SNO);
                    try {
                        connectDB.resultset.next();
                        if (!Objects.equals(connectDB.resultset.getString("SNAME"), jTextField_Sname.getText()) ||
                                !Objects.equals(connectDB.resultset.getString("SAGE"), jTextField_Sage.getText()) ||
                                !Objects.equals(connectDB.resultset.getString("SSEX"), jTextField_Ssex.getText()))
                            JOptionPane.showMessageDialog(null, "修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
                        else {
                            JOptionPane.showMessageDialog(null, "修改成功！");
                            SNAME = jTextField_Sname.getText();
                            SAGE = jTextField_Sage.getText();
                            SSEX = jTextField_Ssex.getText();
                            jLabel_LoginUserName.setText("当前登录用户：" + SNAME);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else
                    JOptionPane.showMessageDialog(null, "不能修改为空！", "错误", JOptionPane.ERROR_MESSAGE);
            });
            change_Person.add(button);
            change_Person.setVisible(true);
        });//修改个人信息代码实现
        init_jtable();
        jMenuItem_Courses_add.addActionListener(e -> {
            ConnectDB connectDB = new ConnectDB();
            connectDB.DoSql("SELECT course.cno,course.cname,tname,tname,ccredit from course,teacherteaching WHERE course.cno=teacherteaching.cno AND course.cno NOT IN(SELECT cno FROM sc WHERE sno=" + SNO + ")");
            int number_Of_columns = 0;
            try {
                while (connectDB.resultset.next())//获取未选课数
                    number_Of_columns++;
                JFrame jFrame = new JFrame("添加课程");
                jFrame.setLayout(new BorderLayout());
                jFrame.setBounds(500, 200, 500, 300);
                JLabel jLabel1 = new JLabel("<html><body><br/><br/>请勾选要选择的课程</body></html>", JLabel.CENTER);
                jLabel1.setFont(new Font("华文新魏", 1, 15));
                jFrame.add(jLabel1, BorderLayout.NORTH);
                String[] jTable_name = {"课程号", "课程名", "授课教师", "学分", "是否勾选"};
                String[] jTable_DBname = {"CNO", "CNAME", "TNAME", "CCREDIT", "check"};
                Object[][] tableData = new Object[number_Of_columns][5];
                connectDB.DoSql("SELECT course.cno,course.cname,tname,ccredit from course,teacherteaching WHERE course.cno=teacherteaching.cno AND course.cno NOT IN(SELECT cno FROM sc WHERE sno=" + SNO + ")");
                connectDB.resultset.next();
                for (int i = 0; i < number_Of_columns; i++) {
                    for (int j = 0; j < 4; j++) {
                        tableData[i][j] = connectDB.resultset.getString(jTable_DBname[j]);
                    }
                    tableData[i][4] = false;
                    connectDB.resultset.next();
                }
                JTable jTable = new JTable(tableData, jTable_name);
                jTable.setRowHeight(30);
                jTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JCheckBox()));
                jFrame.add(new JScrollPane(jTable), BorderLayout.CENTER);
                JButton jButton = new JButton("确定");
                int finalNumber_Of_columns = number_Of_columns;
                jButton.addActionListener(e1 -> {
                    Object obj = true;
                    for (int i = 0; i < finalNumber_Of_columns; i++) {
                        if (tableData[i][4] == obj) {
                            connectDB.DoSql("INSERT INTO sc VALUES(" + SNO + "," + tableData[i][0] + ",null)");
                        }

                    }
                    jFrame.setVisible(false);
                    try {
                        flash_table();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                jFrame.add(jButton, BorderLayout.SOUTH);
                jFrame.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });//添加课程代码实现
        jMenuItem_Courses_delete.addActionListener(e -> {
            ConnectDB connectDB = new ConnectDB();
            connectDB.DoSql("SELECT course.cno,course.cname,tname,tname,ccredit from course,teacherteaching WHERE course.cno=teacherteaching.cno AND course.cno IN(SELECT cno FROM sc WHERE sno=" + SNO + ")");
            int number_Of_columns = 0;
            try {
                while (connectDB.resultset.next())//获取未选课数
                    number_Of_columns++;
                JFrame jFrame = new JFrame("添加课程");
                jFrame.setLayout(new BorderLayout());
                jFrame.setBounds(500, 200, 500, 300);
                JLabel jLabel1 = new JLabel("<html><body><br/><br/>请勾选要选择的课程</body></html>", JLabel.CENTER);
                jLabel1.setFont(new Font("华文新魏", 1, 15));
                jFrame.add(jLabel1, BorderLayout.NORTH);
                String[] jTable_name = {"课程号", "课程名", "授课教师", "学分", "是否勾选"};
                String[] jTable_DBname = {"CNO", "CNAME", "TNAME", "CCREDIT", "check"};
                Object[][] tableData = new Object[number_Of_columns][5];
                connectDB.DoSql("SELECT course.cno,course.cname,tname,ccredit from course,teacherteaching WHERE course.cno=teacherteaching.cno AND course.cno IN(SELECT cno FROM sc WHERE sno=" + SNO + ")");
                connectDB.resultset.next();
                for (int i = 0; i < number_Of_columns; i++) {
                    for (int j = 0; j < 4; j++) {
                        tableData[i][j] = connectDB.resultset.getString(jTable_DBname[j]);
                    }
                    tableData[i][4] = true;
                    connectDB.resultset.next();
                }
                JTable jTable = new JTable(tableData, jTable_name);
                jTable.setRowHeight(30);
                jTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JCheckBox()));
                jFrame.add(new JScrollPane(jTable), BorderLayout.CENTER);
                JButton jButton = new JButton("确定");
                int finalNumber_Of_columns = number_Of_columns;
                jButton.addActionListener(e1 -> {
                    Object obj = false;
                    for (int i = 0; i < finalNumber_Of_columns; i++) {
                        if (tableData[i][4] == obj) {
                            connectDB.DoSql("delete from sc where CNO=" + tableData[i][0] + " and sno=" + SNO);
                        }
                    }
                    jFrame.setVisible(false);
                    try {
                        flash_table();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
                jFrame.add(jButton, BorderLayout.SOUTH);
                jFrame.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });//删除课程代码实现
        jMenuItem_Statistics_grade.addActionListener(e -> {
            jTable_init = null;
            jTable_grade = null;
            remove(jScrollPane);
            try {
                setjTable_grade();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            revalidate();
        });
        jMenuItem_Statistics_credit.addActionListener(e -> {
            ConnectDB connectDB = new ConnectDB();
            connectDB.DoSql("Select * from studentinfo where sno=" + SNO);
            String credit = null;
            String valid_credit = null;
            try {
                connectDB.resultset.next();
                credit = connectDB.resultset.getString("总学分");
                valid_credit = connectDB.resultset.getString("已获得学分");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "当前已选课程总学分：" + credit + "\n当前已获得学分：" + valid_credit);
        });
    }

    void init_jtable() throws SQLException {
        ConnectDB connectDB = new ConnectDB();
        connectDB.DoSql("SELECT * FROM studentselection WHERE sno=" + SNO);
        int number_Of_columns = 0;
        while (connectDB.resultset.next())//获取已选课数
            number_Of_columns++;
        String[] jTable_init_name = {"学号", "姓名", "课程"};
        String[] jTable_init_DBname = {"SNO", "SNAME", "CNAME"};
        Object[][] tableData = new Object[number_Of_columns][3];
        connectDB.DoSql("SELECT * FROM studentselection WHERE sno=" + SNO);
        connectDB.resultset.next();
        for (int i = 0; i < number_Of_columns; i++) {
            for (int j = 0; j < 3; j++) {
                tableData[i][j] = connectDB.resultset.getString(jTable_init_DBname[j]);
            }
            connectDB.resultset.next();
        }
        jTable_init = new JTable(tableData, jTable_init_name);
        jTable_init.getTableHeader().setReorderingAllowed(false);
        jTable_init.setRowHeight(35);
        jTable_init.setEnabled(false);
        add(jScrollPane = new JScrollPane(jTable_init), BorderLayout.CENTER);
    }

    void setjTable_grade() throws SQLException {
        ConnectDB connectDB = new ConnectDB();
        connectDB.DoSql("SELECT * FROM studentselection WHERE sno=" + SNO);
        int number_Of_columns = 0;
        while (connectDB.resultset.next())//获取已选课数
            number_Of_columns++;
        String[] jTable_init_name = {"学号", "姓名", "课程", "成绩"};
        String[] jTable_init_DBname = {"SNO", "SNAME", "CNAME", "GRADE"};
        Object[][] tableData = new Object[number_Of_columns][4];
        connectDB.DoSql("SELECT * FROM studentselection WHERE sno=" + SNO);
        connectDB.resultset.next();
        for (int i = 0; i < number_Of_columns; i++) {
            for (int j = 0; j < 4; j++) {
                tableData[i][j] = connectDB.resultset.getString(jTable_init_DBname[j]);
            }
            connectDB.resultset.next();
        }
        jTable_grade = new JTable(tableData, jTable_init_name);
        jTable_grade.getTableHeader().setReorderingAllowed(false);
        jTable_grade.setRowHeight(35);
        jTable_grade.setEnabled(false);
        add(jScrollPane = new JScrollPane(jTable_grade), BorderLayout.CENTER);
    }

    void flash_table() throws SQLException {
        jTable_init = null;
        jTable_grade = null;
        remove(jScrollPane);
        init_jtable();
        revalidate();
    }
}

