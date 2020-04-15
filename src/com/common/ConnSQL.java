package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnSQL implements AutoCloseable {
    private static final String url="jdbc:mysql://106.13.8.174:3306/Tieba?useSSL=false&serverTimezone=GMT";
    private static final String usr="root";
    private static final String pwd="147158zxc26795";

    private Connection connection;

    public Connection getConn(boolean isAutoCommit) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,usr,pwd);
            connection.setAutoCommit(isAutoCommit);
            log("连接成功, 自动提交设为: "+isAutoCommit);
            return connection;
        } catch(ClassNotFoundException e) {
            log("链接失败, 找不到驱动");
            e.printStackTrace();
            return null;
        } catch(SQLException e) {
            log("链接失败, 数据库错误");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        if(connection!=null){
            try {
                connection.close();
                connection=null;
                log("关闭成功");
            } catch(SQLException e) {
                connection=null;
                log("关闭失败, 关闭时出现错误, 已强制设为null");
                e.printStackTrace();
            }
        } else {
            log("关闭失败, 没有connection对象");
        }
    }

    private static void log(Object o) {
        System.out.println("[ConnSQL]: "+o);
    }
}