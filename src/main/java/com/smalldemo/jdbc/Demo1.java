package com.smalldemo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jim
 * <pre>
 * |- Statement接口： 用于执行静态的sql语句
 *      |- int executeUpdate(String sql)  ： 执行静态的更新sql语句（DDL，DML）
 *      |- ResultSet executeQuery(String sql)  ：执行的静态的查询sql语句（DQL）
 *             |-PreparedStatement接口（Statement接口的子类）：用于执行预编译sql语句
 *                  |- int executeUpdate() ： 执行预编译的更新sql语句（DDL，DML）
 *                  |-ResultSet executeQuery()  ： 执行预编译的查询sql语句（DQL）
 *  </pre>
 */
public class Demo1 {

    public static void main(String[] args) {
//        DDL();
//        showTables();
        desc();
    }

    /**
     * 执行DDL语句（创建表为例）
     */
    public static void DDL() throws RuntimeException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBUtil.getConnection();

            // 创建Statement对象
            statement = connection.createStatement();

            // 要执行的sql语句
            String sql = "CREATE TABLE person(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(10),sex VARCHAR(5),age INT,psot VARCHAR(10),email VARCHAR(20),phone INT)";

            // 使用Statement发送该DDL语句，返回的时该sql语句所影响的行数，int类
            int result = statement.executeUpdate(sql);

            System.out.println(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 最后关闭连接（后开启的先关闭）
        finally {
            DBUtil.close(statement, connection);
        }
    }

    /**
     * show tables
     */
    public static void showTables() throws RuntimeException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBUtil.getConnection();

            // 创建Statement对象
            statement = connection.createStatement();

            // 要执行的sql语句
            String sql = "show tables;";

            // 使用Statement发送该DDL语句，返回的时该sql语句所影响的行数，int类
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Tables_in_test"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 最后关闭连接（后开启的先关闭）
        finally {
            DBUtil.close(statement, connection);
        }
    }

    /**
     *
     */
    public static void desc() throws RuntimeException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBUtil.getConnection();

            // 创建Statement对象
            statement = connection.createStatement();

            // 要执行的sql语句
            String sql = "desc person;";

            // 使用Statement发送该DDL语句，返回的时该sql语句所影响的行数，int类
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String field = resultSet.getString("Field");
                String type = resultSet.getString("Type");
                String null_ = resultSet.getString("Null");
                String key = resultSet.getString("Key");
                String default_ = resultSet.getString("Default");
                String extra = resultSet.getString("Extra");
                System.out.println(field + "\t" + type + "\t" + null_ + "\t" + key + "\t" + default_ + "\t" + extra);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 最后关闭连接（后开启的先关闭）
        finally {
            DBUtil.close(statement, connection);
        }
    }
}
