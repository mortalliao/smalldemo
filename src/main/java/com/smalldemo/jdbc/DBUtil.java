package com.smalldemo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jim
 */
public class DBUtil {

    public static final String url = "jdbc:mysql://120.79.49.117:3306/test?useUnicode=true&characterEncoding=utf8";
    public static final String driver_class_name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123456";

    public static Connection getConnection() throws RuntimeException {
        try {
            //加载驱动程序
            Class.forName(driver_class_name);

            //连接MySQL数据库时，要写上连接的用户名和密码, 获取连接的数据库对象
            return DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Statement statement, Connection connection) {
        // 最后关闭连接（后开启的先关闭）
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
