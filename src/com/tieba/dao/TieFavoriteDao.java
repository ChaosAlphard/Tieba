package com.tieba.dao;

import com.tieba.common.SQLHandler;
import com.tieba.model.TieFavorite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TieFavoriteDao {
    public List<TieFavorite> findFavoriteTies(int UID) {
        String sql = "SELECT * FROM tiefavorite WHERE UID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, UID);

        return SQLHandler.queryMultiple(sql, map, TieFavorite.class);
    }
    public boolean isFavoriteTie(int UID,int tieID) {
        String sql = "SELECT * FROM tiefavorite WHERE TieID=? AND UID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, tieID);
        map.put(2, UID);

        TieFavorite tieFavorite = SQLHandler.querySingle(sql ,map, TieFavorite.class);

        return tieFavorite != null;
    }

    public int favoriteTie(int UID,int tieID,String tieTitle) {
        String sql = "INSERT INTO tiefavorite(UID, TieID, TieTitle) VALUES (?,?,?)";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, UID);
        map.put(2, tieID);
        map.put(3, tieTitle);

        return SQLHandler.update(sql, map);
    }
    public int unfavoriteTie(int UID,int tieID) {
        String sql = "DELETE FROM tiefavorite WHERE UID=? AND TieID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, UID);
        map.put(2, tieID);

        return SQLHandler.update(sql, map);
    }
}