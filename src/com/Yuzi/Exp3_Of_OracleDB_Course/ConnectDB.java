package com.Yuzi.Exp3_Of_OracleDB_Course;

import java.sql.*;

class ConnectDB {
    private Connection connection = null;
    public ResultSet resultset = null;

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "final_work", "114514");
        return connection;
    }

    public void DoSql(String sql) {
        try {
            connection = connect();
            Statement statement = connection.createStatement();
            System.out.println(sql);//for debug
            resultset = statement.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

