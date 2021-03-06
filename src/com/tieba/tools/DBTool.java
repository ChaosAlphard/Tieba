package com.tieba.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBTool {
    private static final LogTool log = LogTool.of(DBTool.class);

    private static<T> T setEntity(ResultSet rs, Class<T> data) {
        Field[] declaredFields = data.getDeclaredFields();
        T t = getNewInstance(data);
        if(t == null) {
            return null;
        }

        for(Field field : declaredFields) {
            // 跳过静态字段, getModifiers方法不受private访问限制
            if(Modifier.isStatic(field.getModifiers())) { continue; }

            boolean flag = field.isAccessible();
            field.setAccessible(true);
            Class<?> type = field.getType();

            try {
                if(type == String.class) {
                    field.set(t,rs.getString(field.getName()));
                }
                else if(type == Integer.class || type == int.class) {
                    field.set(t,rs.getInt(field.getName()));
                }
                else {
                    log.error("匹配不到对应类型: "+field.getType()+" 字段: "+field.getName());
                }
            } catch(IllegalAccessException e) {
                log.exception("ResultSet映射到DataEntity失败\n类型: "
                        +field.getType()+" 字段: "+field.getName(), e);
                e.printStackTrace();
            } catch(SQLException e){
                log.error("ResultSet中找不到对应的字段: "+field.getName());
            } finally {
                field.setAccessible(flag);
            }

        }
        return t;
    }

    public static<T> T setData(ResultSet rs, Class<T> data) throws SQLException {
        if(rs.next()) {
            return setEntity(rs, data);
        } else {
            log.warn("ResultSet中找不到数据");
            return null;
        }
    }

    public static<T> List<T> setDataList(ResultSet rs, Class<T> data) throws SQLException {
        List<T> lis = new ArrayList<>();
        while(rs.next()) {
            lis.add(setEntity(rs, data));
        }
        return lis;
    }

    private static<T> T getNewInstance(Class<T> data) {
        // 返回新的T实例
        try {
            return data.newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            log.error("创建DataEntity失败, 实体类必须要有一个公共(public)的无参构造方法!");
            e.printStackTrace();
            return null;
        }
    }
}
