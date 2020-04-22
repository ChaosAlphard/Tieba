package com.tieba.common;

import com.tieba.tools.TimeTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnSQL implements AutoCloseable {
    private static final String url="jdbc:mysql://127.0.0.1:3306/Tieba?useSSL=false&serverTimezone=GMT&useUnicode=true&characterEncoding=utf8";
    private static final String usr="root";
    private static final String pwd="123456";

    private Connection connection;

    public ConnSQL() {
        this(true);
    }

    public ConnSQL(boolean isAutoCommit) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url,usr,pwd);
            this.connection.setAutoCommit(isAutoCommit);
            logInfo("连接成功, 自动提交设为: "+isAutoCommit);
        } catch(ClassNotFoundException e) {
            logError("链接失败, 找不到驱动\n"+e.getMessage());
        } catch(SQLException e) {
            logError("链接失败, 数据库错误\n"+e.getMessage());
        }
    }

    public Connection getConn() {
        return this.connection;
    }

    @Override
    public void close() {
        if(connection!=null){
            try {
                connection.close();
                connection=null;
                logInfo("关闭成功");
            } catch(SQLException e) {
                connection=null;
                logError("关闭失败, 关闭时出现错误, 已强制设为null\n"+e.getMessage());
            }
        } else {
            logError("关闭失败, 没有connection对象");
        }
    }

    private static void logInfo(Object o) {
        System.out.println(TimeTool.getCurrentTime()+" ConnSQL[ Info ]: "+o);
    }

    private static void logError(Object o) {
        System.out.println(TimeTool.getCurrentTime()+" ConnSQL[ Error ]: "+o);
    }
}