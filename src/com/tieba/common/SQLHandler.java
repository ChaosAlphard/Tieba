package com.tieba.common;

import com.tieba.tools.DBTool;
import com.tieba.tools.TimeTool;

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
            printSQL(pst);

            return t;
        } catch(SQLException e) {
            error("查询失败");
            e.printStackTrace();
            return null;
        } catch(NullPointerException e) {
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
            printSQL(pst);

            return lis;
        } catch(SQLException e) {
            error("查询失败");
            e.printStackTrace();
            return new ArrayList<>(0);
        } catch(NullPointerException e) {
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
                rs.close();
                return null;
            }

            T t = null;
            switch(clazz.getTypeName()) {
                case "java.lang.String": {
                    t =  (T)rs.getString(1);
                    break;
                }
                case "int":
                case "java.lang.Integer": {
                    t =  (T)Integer.valueOf(rs.getInt(1));
                    break;
                }
                default: {
                    error("无法匹配对应的Column类型: "+clazz.getTypeName());
                    break;
                }
            }

            rs.close();
            printSQL(pst);
            return t;
        } catch(SQLException e) {
            error("查询失败");
            e.printStackTrace();
            return null;
        } catch(NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int update(String sql, Map<Integer, Object> params) {
        try(ConnSQL conn = new ConnSQL(true);
            PreparedStatement pst = conn.getConn().prepareStatement(sql)) {

            setParams(pst, params);

            int res = pst.executeUpdate();

            printSQL(pst);
            return res;
        } catch(SQLException e) {
            error("更新失败");
            e.printStackTrace();
            return 0;
        } catch(NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void setParams(PreparedStatement pst, Map<Integer, Object> params) throws SQLException {
        if(params == null) {
            return;
        }
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

    private static void printSQL(PreparedStatement pst) {
        info(pst.toString().split(": ")[1]);
    }

    private static void info(Object o) {
        System.out.println(TimeTool.getCurrentTime()+" SQLHandler [ Info ]: "+o);
    }

    private static void error(Object o) {
        System.out.println(TimeTool.getCurrentTime()+" SQLHandler [ Error ]: "+o);
    }
}