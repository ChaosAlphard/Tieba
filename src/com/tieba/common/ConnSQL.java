package com.tieba.common;

import com.tieba.tools.LogTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnSQL implements AutoCloseable {
    private static final String url="jdbc:mysql://127.0.0.1:3306/Tieba?useSSL=false&serverTimezone=GMT&useUnicode=true&characterEncoding=utf8";
    private static final String usr="root";
    private static final String pwd="123456";

    private Connection connection;

    private static final LogTool log = LogTool.of(ConnSQL.class);

    public ConnSQL() {
        this(true);
    }

    public ConnSQL(boolean isAutoCommit) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url,usr,pwd);
            this.connection.setAutoCommit(isAutoCommit);
            log.info("连接成功, 自动提交设为: "+isAutoCommit);
        } catch(ClassNotFoundException e) {
            log.exception("链接失败, 找不到驱动", e);
        } catch(SQLException e) {
            log.exception("链接失败, 数据库错误", e);
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
                log.info("关闭成功");
            } catch(SQLException e) {
                connection=null;
                log.exception("关闭失败, 关闭时出现错误, 已强制设为null", e);
            }
        } else {
            log.error("关闭失败, 没有connection对象");
        }
    }
}