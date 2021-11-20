package com.Yuzi.Exp3_Of_OracleDB_Course;

import java.sql.*;

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
