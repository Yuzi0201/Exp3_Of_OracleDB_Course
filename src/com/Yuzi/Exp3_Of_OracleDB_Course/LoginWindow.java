package com.Yuzi.Exp3_Of_OracleDB_Course;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

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
                    JOptionPane.showMessageDialog(null, "错误的用户名或密码！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String sql = "select * from student where SNO=" + number.getText() + " and PWD=" + password.getText();
                ConnectDB connectDB = new ConnectDB();
                connectDB.DoSql(sql);
                try {
                    if (!connectDB.resultset.next()) {
                        JOptionPane.showMessageDialog(null, "错误的用户名或密码！", "错误", JOptionPane.ERROR_MESSAGE);
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
