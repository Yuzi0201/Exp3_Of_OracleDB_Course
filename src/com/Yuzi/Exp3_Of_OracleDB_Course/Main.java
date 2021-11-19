package com.Yuzi.Exp3_Of_OracleDB_Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

class ConnectDB {
    private Connection connection = null;
    private Statement statement = null;
    public ResultSet resultset = null;

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "final_work", "114514");
        return connection;
    }

    public void DoSql(String sql) {
        try {
            connection = connect();
            statement = connection.createStatement();
            System.out.println(sql);//for debug
            resultset = statement.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

class LoginWindow extends JFrame {
    JTextField number;
    JButton button;
    JPasswordField password;

    public LoginWindow() {
        init();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {
        setLayout(null);
        JLabel jl1 = new JLabel("用户名：");
        jl1.setBounds(30, 50, 100, 30);
        add(jl1);
        number = new JTextField(10);
        number.setBounds(80, 50, 150, 30);
        add(number);
        password = new JPasswordField(10);
        JLabel jl2 = new JLabel("密码：");
        jl2.setBounds(42, 100, 100, 30);
        add(jl2);
        password.setBounds(80, 100, 150, 30);
        add(password);
        button = new JButton("确定");
        button.setBounds(115, 150, 70, 30);
        add(button);
        ButtonAction button_action = new ButtonAction();
        button.addActionListener(button_action);
    }

    private class ButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Objects.equals(number.getText(), "admin")) {
                if (Objects.equals(password.getText(), "1919810")) {
                    setVisible(false);
                    AdminWindow adminwindow = new AdminWindow();
                } else {
                    JFrame errordialog = new JFrame();
                    JOptionPane.showMessageDialog(errordialog, "错误的用户名或密码！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String sql = "select * from student where SNO=" + number.getText() + " and PWD=" + password.getText();
                ConnectDB connectDB = new ConnectDB();
                connectDB.DoSql(sql);
                System.out.println(sql);
                try {
                    if (!connectDB.resultset.next()) {
                        JFrame errordialog = new JFrame();
                        JOptionPane.showMessageDialog(errordialog, "错误的用户名或密码！", "错误", JOptionPane.ERROR_MESSAGE);
                    } else {
                        setVisible(false);
                        StudentWindow studentWindow = new StudentWindow(number.getText());
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}


class AdminWindow extends JFrame {
    public AdminWindow() {
        init();
        setBounds(50, 20, 900, 600);
        setTitle("管理员界面");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {
        setLayout(null);

    }
}

class StudentWindow extends JFrame {
    private String SNO;
    private String SNAME;
    private String SSEX;
    private String SAGE;
    private JLabel jLabel_LoginUserName;

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
        JLabel label1 = new JLabel("<html><body><br/><br/>欢迎使用学生课程系统！</body></html>", JLabel.CENTER);
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
        JMenuItem jMenuItem_Statistics_credit = new JMenuItem("查询已获得学分");
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
        });
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
                System.out.println(jTextField_Sage.getText());
                if (jTextField_Sage.getText().length()!=0 && jTextField_Sname.getText().length()!=0 && jTextField_Ssex.getText().length()!=0) {
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
                            SNAME=jTextField_Sname.getText();
                            SAGE=jTextField_Sage.getText();
                            SSEX=jTextField_Ssex.getText();
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
        });
    }
}

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        LoginWindow win = new LoginWindow();
        win.setBounds(500, 200, 310, 260);
        win.setTitle("登录");
        Connection conn1 = null;
        Statement statement = null;
        ResultSet resultset = null;
        ConnectDB connectDB = new ConnectDB();
        conn1 = connectDB.connect();
        statement = conn1.createStatement();
        resultset = statement.executeQuery("select * from student");
        while (resultset.next()) {
            System.out.println(resultset.getString("SNO") + "   " + resultset.getString("SNAME") + "   " + resultset.getString("PWD"));  //打印输出结果集
        }
    }
}
