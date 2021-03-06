package com.tieba.dao;

import com.tieba.common.SQLHandler;
import com.tieba.model.Bar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarDao {
    public List<Bar> FindBars(String bars) {
        String sql = "SELECT * FROM bars WHERE Visible=1 AND BarName like ?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, '%'+bars+'%');

        return SQLHandler.queryMultiple(sql, map, Bar.class);
    }

    public Bar FindById(int barID) {
        String sql = "SELECT * FROM bars WHERE BarID=? limit 1";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, barID);

        return SQLHandler.querySingle(sql, map, Bar.class);
    }

    public boolean isBarExist(String barName) {
        String sql="SELECT BarName FROM bars WHERE BarName=? limit 1";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, barName);

        String res = SQLHandler.queryColumn(sql, map, String.class);

        return (res != null && !res.isEmpty());
    }

    public int createNewBar(String barName,String barContent) {
        String sql="INSERT INTO bars(BarName, BarContent, Visible) VALUES(?,?,1)";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, barName);
        map.put(2, barContent);

        return SQLHandler.update(sql, map);
    }
}
