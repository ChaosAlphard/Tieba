package com.common;

import com.tools.DBTool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLHandler {
    public static<T> T queryData(String sql, Map<Integer, Object> params, Class<T> clazz) {
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

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

            final ResultSet rs = pst.executeQuery();
            T t = DBTool.setData(rs, clazz);
            rs.close();

            return t;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static<T> List<T> queryDataList(String sql, Map<Integer, Object> params, Class<T> clazz) {
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

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

            final ResultSet rs = pst.executeQuery();
            List<T> lis = DBTool.setDataList(rs, clazz);
            rs.close();

            return lis;
        } catch(SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(0);
        }
    }

    private static void error(Object o) {
        System.out.println("SQLHandler [ Error ]: "+o);
    }
}
