package com.Yuzi.Exp3_Of_OracleDB_Course;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        LoginWindow win = new LoginWindow();
        win.setBounds(500, 200, 310, 260);
        win.setTitle("登录");
        ConnectDB connectDB = new ConnectDB();
        connectDB.DoSql("select * from student");
        while (connectDB.resultset.next()) {
            System.out.println(connectDB.resultset.getString("SNO") + "   " + connectDB.resultset.getString("SNAME")
                    + "   " + connectDB.resultset.getString("PWD"));  //打印输出结果集
        }
    }
}
