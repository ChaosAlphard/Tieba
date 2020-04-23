package com.tieba.dao;

import com.tieba.common.SQLHandler;
import com.tieba.model.Tie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TieDao {
    public Tie FindByID(int tieID) {
        String sql = "SELECT * FROM ties WHERE TieID=? limit 1";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, tieID);

        return SQLHandler.querySingle(sql, map, Tie.class);
    }

    public List<Tie> FindByBarID(int barID) {
        String sql = "SELECT * FROM ties WHERE Visible=1 AND BarID=? ORDER BY UpdateTime DESC";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, barID);

        return SQLHandler.queryMultiple(sql, map, Tie.class);
    }

    public List<Tie> findEliteByBarID(int barID) {
        String sql = "SELECT * FROM ties WHERE Elite=1 AND Visible=1 AND BarID=? ORDER BY UpdateTime DESC";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, barID);

        return SQLHandler.queryMultiple(sql, map, Tie.class);
    }

    public List<Tie> findDeletedTie(String tieUser) {
        String sql = "SELECT * FROM ties WHERE Visible=0 AND TieUser=? ORDER BY PostTime DESC";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, tieUser);

        return SQLHandler.queryMultiple(sql, map, Tie.class);
    }

    public List<Tie> FindByPage(int barID, int pageStart, int pageAmount) {
        String sql = "SELECT * from ties WHERE BarID=? AND TieID>=? LIMIT ?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, barID);
        map.put(2, (pageStart-1)*pageAmount+1);
        map.put(3, pageAmount);

        return SQLHandler.queryMultiple(sql,map,Tie.class);
    }

    public List<Tie> findRecentlyTie() {
        String sql = "SELECT * FROM ties WHERE Visible=1 ORDER BY UpdateTime DESC LIMIT 10";

        Map<Integer, Object> map = new HashMap<>();

        return SQLHandler.queryMultiple(sql, null, Tie.class);
    }

    public int deleteTie(int tieID) {
        String sql = "UPDATE ties SET Visible=0 WHERE TieID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, tieID);

        return SQLHandler.update(sql, map);
    }

    public int restoreTie(int tieID) {
        String sql = "UPDATE ties SET Visible=1 WHERE TieID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, tieID);

        return SQLHandler.update(sql, map);
    }

    public int setTieElite(int tieID) {
        String sql = "UPDATE ties SET Elite=1 WHERE TieID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, tieID);

        return SQLHandler.update(sql, map);
    }

    public int unsetTieElite(int tieID) {
        String sql = "UPDATE ties SET Elite=0 WHERE TieID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, tieID);

        return SQLHandler.update(sql, map);
    }
}
