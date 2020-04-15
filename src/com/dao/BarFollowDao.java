package com.dao;

import com.common.SQLHandler;
import com.model.BarFollow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarFollowDao {
    public List<BarFollow> findFollowBars(int UID) {
        String sql = "SELECT * FROM barfollow WHERE UID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, UID);

        return SQLHandler.queryMultiple(sql, map, BarFollow.class);
    }

    public boolean isFollowBar(int UID, int barID) {
        String sql = "SELECT * FROM barfollow WHERE BarID=? AND UID=? limit 1";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, barID);
        map.put(2, UID);

        BarFollow barFollow = SQLHandler.querySingle(sql, map, BarFollow.class);

        return barFollow!=null;
    }

    public int followBar(int UID, int barID, String barName) {
        String sql = "INSERT INTO barfollow(UID, BarID, BarName) VALUES (?,?,?)";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, UID);
        map.put(2, barID);
        map.put(3, barName);

        return SQLHandler.update(sql, map);
    }

    public int unfollowBar(int UID, int barID) {
        String sql = "DELETE FROM barfollow WHERE UID=? AND BarID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, UID);
        map.put(2, barID);

        return SQLHandler.update(sql, map);
    }
}