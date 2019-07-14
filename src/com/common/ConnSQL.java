package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnSQL {
    private static Connection connection;
    public static Connection getConn(){
        if(connection==null){
            connection = getConnection();
        } else {
            try {
                if(connection.isClosed()){
                    connection = getConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static Connection getNoAutoCommitConn() throws SQLException {
        Connection naConnection = getConnection();
        naConnection.setAutoCommit(false);
        System.out.println("ConnSQL.getNoAutoCommit[success]");
        return naConnection;
    }

    private static Connection getConnection(){
        Connection conn = null;
        String url="jdbc:mysql://localhost:3306/tieba?useSSL=false";
        String usr="root";
        String pwd="123456";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,usr,pwd);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            System.out.println("链接失败, 找不到驱动");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("链接失败, 数据库错误");
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeSQL(){
        if(connection!=null){
            try {
                connection.close();
                connection=null;
                System.out.println("关闭成功");
            } catch (SQLException e) {
                connection=null;
                System.out.println("关闭失败, 关闭时出现错误, 已强制设为null");
                e.printStackTrace();
            }
        } else {
            System.out.println("关闭失败, 没有connection对象");
        }
    }
}
