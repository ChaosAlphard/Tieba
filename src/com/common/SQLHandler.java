package com.common;

import com.tools.DBTool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLHandler {
    public static<T> T querySingle(String sql, Map<Integer, Object> params, Class<T> clazz) {
        try(ConnSQL conn = new ConnSQL(true);
            PreparedStatement pst = conn.getConn().prepareStatement(sql)) {

            setParams(pst, params);

            final ResultSet rs = pst.executeQuery();
            T t = DBTool.setData(rs, clazz);
            rs.close();

            return t;
        } catch(SQLException e) {
            error("查询失败");
            e.printStackTrace();
            return null;
        }
    }

    public static<T> List<T> queryMultiple(String sql, Map<Integer, Object> params, Class<T> clazz) {
        try(ConnSQL conn = new ConnSQL(true);
            PreparedStatement pst = conn.getConn().prepareStatement(sql)) {

            setParams(pst, params);

            final ResultSet rs = pst.executeQuery();
            List<T> lis = DBTool.setDataList(rs, clazz);
            rs.close();

            return lis;
        } catch(SQLException e) {
            error("查询失败");
            e.printStackTrace();
            return new ArrayList<>(0);
        }
    }

    public static<T> T queryColumn(String sql, Map<Integer, Object> params, Class<T> clazz) {
        try(ConnSQL conn = new ConnSQL(true);
            PreparedStatement pst = conn.getConn().prepareStatement(sql)) {

            setParams(pst, params);

            final ResultSet rs = pst.executeQuery();
            if(!rs.next()) {
                return null;
            }

            switch(clazz.getTypeName()) {
                case "java.lang.String": {
                    return (T)rs.getString(1);
                }
                case "int":
                case "java.lang.Integer": {
                    return (T)Integer.valueOf(rs.getInt(1));
                }
                default: {
                    error("无法匹配对应的Column类型: "+clazz.getTypeName());
                    return null;
                }
            }
        } catch(SQLException e) {
            error("查询失败");
            e.printStackTrace();
            return null;
        }
    }

    private static<T> T getNewInstance(Class<T> data, Object init) {
        // 返回新的T实例
        try {
            return data.newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            error("创建DataEntity失败, 实体类必须要有一个公共(public)的无参构造方法!");
            e.printStackTrace();
            return null;
        }
    }

    public static int queryCount(String sql, Map<Integer, Object> params) {
        try(ConnSQL conn = new ConnSQL(true);
            PreparedStatement pst = conn.getConn().prepareStatement(sql)) {

            setParams(pst, params);

            final ResultSet rs = pst.executeQuery();

            int count = 0;
            if(rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();

            return count;
        } catch(SQLException e) {
            error("统计失败");
            e.printStackTrace();
            return -1;
        }
    }

    public static int update(String sql, Map<Integer, Object> params) {
        try(ConnSQL conn = new ConnSQL(true);
            PreparedStatement pst = conn.getConn().prepareStatement(sql)) {

            setParams(pst, params);

            return pst.executeUpdate();
        } catch(SQLException e) {
            error("更新失败");
            e.printStackTrace();
            return -1;
        }
    }

    private static void setParams(PreparedStatement pst, Map<Integer, Object> params) throws SQLException {
        for(Map.Entry<Integer, Object> entry : params.entrySet()) {
            int key = entry.getKey();
            Object value = entry.getValue();

            if(value instanceof String){
                pst.setString(key, (String)value);
            }
            else if(value instanceof Integer){
                pst.setInt(key, (int)value);
            }
            else {
                error("设置查询参数失败["+key+"]: "+value);
            }
        }
    }

    private static void error(Object o) {
        System.out.println("SQLHandler [ Error ]: "+o);
    }
}
