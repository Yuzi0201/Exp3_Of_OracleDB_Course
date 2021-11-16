package com.Yuzi.Exp3_Of_OracleDB_Course;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

class ConnectDB{
    public Connection connect() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "final_work", "114514");
        if (connection != null)
            System.out.println("Connected with connection final_work");
        return connection;
    }
}

class LoginWindow extends JFrame{
    JTextField number;
    JButton button;
    JPasswordField password;

    public LoginWindow() {
        init();
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
        ButtonAction button_action=new ButtonAction();
        button.addActionListener(button_action);
    }
    private class ButtonAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Objects.equals(number.getText(), "admin")){
                if (Objects.equals(password.getText(), "1919810")){

                }
                else {
                    JFrame errordialog = new JFrame();
                    JOptionPane.showMessageDialog(errordialog, "错误的用户名或密码！");
                }
            }
            else {
                Connection connection = null;
                Statement statement = null;
                ResultSet resultset = null;
                ConnectDB connectDB = new ConnectDB();
                try {
                    connection = connectDB.connect();
                    statement = connection.createStatement();
                    String sql = "select * from student where SNO=" + number.getText() + " and PWD=" + password.getText();
                    System.out.println(sql);
                    resultset = statement.executeQuery(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                if (resultset == null) {
                    JFrame errordialog = new JFrame();
                    JOptionPane.showMessageDialog(errordialog, "错误的用户名或密码！");
                } else {

                }
            }
        }
    }
}

class AdminWindow extends JFrame{
    public AdminWindow(){

    }
}

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        LoginWindow win = new LoginWindow();
        win.setBounds(500, 200, 310, 260);
        win.setTitle("登录");
        Connection conn1 = null;
        Statement statement = null;
        ResultSet resultset=null;
        ConnectDB connectDB = new ConnectDB();
        conn1=connectDB.connect();
        statement = conn1.createStatement();
        resultset=statement.executeQuery("select * from student");
        while (resultset.next())
        {
            System.out.println(resultset.getString("SNO")+"   "+resultset.getString("SNAME")+"   "+resultset.getString("PWD"));  //打印输出结果集
        }
    }
}
