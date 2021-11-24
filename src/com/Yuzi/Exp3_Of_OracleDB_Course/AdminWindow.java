package com.Yuzi.Exp3_Of_OracleDB_Course;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

class AdminWindow extends JFrame {
    public AdminWindow() {
        init();
        setBounds(50, 20, 900, 600);
        setTitle("管理员界面");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {
        setLayout(new BorderLayout());
        JLabel label1 = new JLabel("<html><body><br/><br/>欢迎使用学生课程系统！<br/><br/></body></html>", JLabel.CENTER);
        label1.setFont(new Font("华文新魏", 1, 25));
        add(label1, BorderLayout.NORTH);
        JLabel label2 = new JLabel("当前登录用户：admin");
        label2.setFont(new Font("仿宋", 0, 15));
        add(label2, BorderLayout.SOUTH);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu jMenu_Pwd = new JMenu("修改密码");
        jMenu_Pwd.setPreferredSize(new Dimension(65, 30));
        menuBar.add(jMenu_Pwd);
        JMenu jMenu_Edit = new JMenu("维护");
        jMenu_Edit.setPreferredSize(new Dimension(45, 30));
        menuBar.add(jMenu_Edit);
        JMenu jMenu_Statistics = new JMenu("统计");
        jMenu_Statistics.setPreferredSize(new Dimension(45, 30));
        menuBar.add(jMenu_Statistics);
        JMenuItem jMenuItem_Pwd_changeadminpwd = new JMenuItem("修改管理员密码");
        jMenuItem_Pwd_changeadminpwd.setPreferredSize(new Dimension(120, 30));
        jMenu_Pwd.add(jMenuItem_Pwd_changeadminpwd);
        JMenuItem jMenuItem_Pwd_changestupwd = new JMenuItem("修改学生密码");
        jMenuItem_Pwd_changestupwd.setPreferredSize(new Dimension(120, 30));
        jMenu_Pwd.add(jMenuItem_Pwd_changestupwd);
        JMenuItem jMenuItem_Edit_stu = new JMenuItem("学生");
        jMenuItem_Edit_stu.setPreferredSize(new Dimension(120, 30));
        jMenu_Edit.add(jMenuItem_Edit_stu);
        JMenuItem jMenuItem_Edit_teacher = new JMenuItem("教师");
        jMenuItem_Edit_teacher.setPreferredSize(new Dimension(120, 30));
        jMenu_Edit.add(jMenuItem_Edit_teacher);
        JMenuItem jMenuItem_Edit_course = new JMenuItem("课程");
        jMenuItem_Edit_course.setPreferredSize(new Dimension(120, 30));
        jMenu_Edit.add(jMenuItem_Edit_course);
        JMenuItem jMenuItem_Statistics_teacher = new JMenuItem("职称教师统计");
        jMenuItem_Statistics_teacher.setPreferredSize(new Dimension(120, 30));
        jMenu_Statistics.add(jMenuItem_Statistics_teacher);
        JMenuItem jMenuItem_Statistics_course = new JMenuItem("课程统计");
        jMenuItem_Statistics_course.setPreferredSize(new Dimension(120, 30));
        jMenu_Statistics.add(jMenuItem_Statistics_course);
        JMenuItem jMenuItem_Statistics_student = new JMenuItem("学生统计");
        jMenuItem_Statistics_student.setPreferredSize(new Dimension(120, 30));
        jMenu_Statistics.add(jMenuItem_Statistics_student);
        jMenuItem_Pwd_changeadminpwd.addActionListener(e -> {
            JPasswordField password;
            JButton button;
            JPasswordField password_repeat;
            JFrame changePwd = new JFrame("修改管理员密码");
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
                        connectDB.DoSql("update student set PWD=" + password.getText() + " where SNO=25565");
                        connectDB.DoSql("select * from student where SNO=25565");
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
        });//修改管理员密码
        jMenuItem_Pwd_changestupwd.addActionListener(e -> {
            JTextField jTextField_sno;
            JTextField jTextField_pwd;
            JButton button;
            JFrame changePwd = new JFrame("修改学生密码");
            changePwd.setBounds(500, 200, 310, 260);
            changePwd.setResizable(false);
            changePwd.setLayout(null);
            JLabel jl1 = new JLabel("学号：");
            jl1.setBounds(30, 50, 100, 30);
            changePwd.add(jl1);
            jTextField_sno = new JTextField(10);
            jTextField_sno.setBounds(80, 50, 150, 30);
            changePwd.add(jTextField_sno);
            jTextField_pwd = new JTextField(10);
            JLabel jl2 = new JLabel("新密码：");
            jl2.setBounds(20, 100, 100, 30);
            changePwd.add(jl2);
            jTextField_pwd.setBounds(80, 100, 150, 30);
            changePwd.add(jTextField_pwd);
            button = new JButton("确定");
            button.setBounds(115, 150, 70, 30);
            changePwd.add(button);
            button.addActionListener(e1 -> {
                ConnectDB connectDB = new ConnectDB();
                connectDB.DoSql("select * from student where sno=" + jTextField_sno.getText());
                try {
                    if (connectDB.resultset.next()) {
                        if (jTextField_pwd.getText() != null) {
                            connectDB.DoSql("update student set PWD=" + jTextField_pwd.getText() + " where SNO=" + jTextField_sno.getText());
                            connectDB.DoSql("select * from student where SNO=" + jTextField_sno.getText());
                            try {
                                connectDB.resultset.next();
                                if (!Objects.equals(connectDB.resultset.getString("PWD"), jTextField_pwd.getText())) {
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
                        JOptionPane.showMessageDialog(null, "此学生不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            });
            changePwd.setVisible(true);
        });//修改学生密码
        jMenuItem_Edit_stu.addActionListener(e -> {
            JFrame jFrame = new JFrame("修改学生信息");
            jFrame.setBounds(500, 200, 310, 200);
            jFrame.setResizable(false);
            jFrame.setLayout(null);
            JLabel jl1 = new JLabel("请输入学生ID：");
            jl1.setBounds(100, 20, 100, 30);
            jFrame.add(jl1);
            JTextField jTextField_sno = new JTextField();
            jTextField_sno.setBounds(80, 50, 150, 30);
            jFrame.add(jTextField_sno);
            JButton jButton_edit = new JButton("修改");
            jButton_edit.setBounds(110, 100, 70, 30);
            jFrame.add(jButton_edit);
            jButton_edit.addActionListener(e1 -> {
                ConnectDB connectDB = new ConnectDB();
                connectDB.DoSql("select * from student where sno=" + jTextField_sno.getText());
                try {
                    if (connectDB.resultset.next()) {
                        jFrame.setVisible(false);
                        JFrame jFrame1 = new JFrame("修改学生信息");
                        jFrame1.setBounds(500, 200, 310, 300);
                        jFrame1.setResizable(false);
                        jFrame1.setLayout(null);
                        JLabel jLabel_sno = new JLabel("学号： " + jTextField_sno.getText());
                        jLabel_sno.setBounds(30, 50, 100, 30);
                        jFrame1.add(jLabel_sno);
                        JLabel jLabel_name = new JLabel("姓名：");
                        jLabel_name.setBounds(30, 90, 100, 30);
                        jFrame1.add(jLabel_name);
                        JTextField jTextField_name = new JTextField(connectDB.resultset.getString("SNAME"));
                        jTextField_name.setBounds(70, 90, 150, 30);
                        jFrame1.add(jTextField_name);
                        JLabel jLabel_sex = new JLabel("性别：");
                        jLabel_sex.setBounds(30, 130, 100, 30);
                        jFrame1.add(jLabel_sex);
                        JTextField jTextField_sex = new JTextField(connectDB.resultset.getString("SSex"));
                        jTextField_sex.setBounds(70, 130, 150, 30);
                        jFrame1.add(jTextField_sex);
                        JLabel jLabel_age = new JLabel("年龄：");
                        jLabel_age.setBounds(30, 170, 100, 30);
                        jFrame1.add(jLabel_age);
                        JTextField jTextField_age = new JTextField(connectDB.resultset.getString("Sage"));
                        jTextField_age.setBounds(70, 170, 100, 30);
                        jFrame1.add(jTextField_age);
                        JButton jButton = new JButton("确定");
                        jButton.setBounds(110, 210, 70, 30);
                        jFrame1.add(jButton);
                        jButton.addActionListener(e2 -> {
                            if (jTextField_age.getText().length() != 0 && jTextField_name.getText().length() != 0 && jTextField_sex.getText().length() != 0) {
                                connectDB.DoSql("update student set SNAME='" + jTextField_name.getText() + "',SAGE='" + jTextField_age.getText() +
                                        "',SSEX='" + jTextField_sex.getText() + "' where SNO=" + jTextField_sno.getText());
                                connectDB.DoSql("select * from student where SNO=" + jTextField_sno.getText());
                                try {
                                    connectDB.resultset.next();
                                    if (!Objects.equals(connectDB.resultset.getString("SNAME"), jTextField_name.getText()) ||
                                            !Objects.equals(connectDB.resultset.getString("SAGE"), jTextField_age.getText()) ||
                                            !Objects.equals(connectDB.resultset.getString("SSEX"), jTextField_sex.getText()))
                                        JOptionPane.showMessageDialog(null, "修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
                                    else {
                                        JOptionPane.showMessageDialog(null, "修改成功！");
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            } else
                                JOptionPane.showMessageDialog(null, "不能修改为空！", "错误", JOptionPane.ERROR_MESSAGE);
                        });
                        jFrame1.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "此学生不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });//修改学生信息
            JButton jButton_add = new JButton("增加");
            jButton_add.setBounds(30, 100, 70, 30);
            jFrame.add(jButton_add);
            jButton_add.addActionListener(e1 -> {
                ConnectDB connectDB = new ConnectDB();
                connectDB.DoSql("select * from student where sno=" + jTextField_sno.getText());
                try {
                    if (!connectDB.resultset.next()) {
                        String sno=jTextField_sno.getText();
                        JFrame jFrame1=new JFrame("增加学生");
                        jFrame1.setBounds(500, 180, 310, 200);
                        jFrame1.setResizable(false);
                        jFrame1.setLayout(null);
                        JLabel jLabel1=new JLabel("请设置密码：");
                        jLabel1.setBounds(30, 30, 100, 30);
                        jFrame1.add(jLabel1);
                        JTextField jTextField=new JTextField();
                        jTextField.setBounds(70,70,150,30);
                        jFrame1.add(jTextField);
                        JButton jButton=new JButton("确定添加");
                        jButton.setBounds(100,120,100,30);
                        jFrame1.add(jButton);
                        jButton.addActionListener(e2 -> {
                            connectDB.DoSql("INSERT INTO student(sno,pwd) VALUES("+sno+","+jTextField.getText()+")");
                            connectDB.DoSql("select * from student where sno="+sno);
                            try {
                                if(connectDB.resultset.next()) {
                                    jFrame1.setVisible(false);
                                    JOptionPane.showMessageDialog(null, "添加成功！");
                                }else{
                                    JOptionPane.showMessageDialog(null, "添加失败！", "错误", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        });
                        jFrame1.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "此学生已存在！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });//增加学生
            JButton jButton_delete = new JButton("删除");
            jButton_delete.setBounds(200, 100, 70, 30);
            jFrame.add(jButton_delete);
            jButton_delete.addActionListener(e1 -> {
                ConnectDB connectDB=new ConnectDB();
                connectDB.DoSql("select * from student where sno="+jTextField_sno.getText());
                try {
                    if(connectDB.resultset.next()){
                        connectDB.DoSql("delete from student where sno="+jTextField_sno.getText());
                        connectDB.DoSql("select * from student where sno="+jTextField_sno.getText());
                        if(connectDB.resultset.next()){
                            JOptionPane.showMessageDialog(null, "删除失败！", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "删除成功！");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "学生不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });//删除学生
            jFrame.setVisible(true);
        });//修改学生信息
    }
}

