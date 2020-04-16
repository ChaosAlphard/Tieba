package com.tieba.dao;

import com.tieba.common.SQLHandler;
import com.tieba.model.TieReply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TieReplyDao {
    public List<TieReply> FindByTieID(int tieID) {
        String sql = "SELECT * FROM tiereply WHERE TieID=? AND Visible=1";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, tieID);

        return SQLHandler.queryMultiple(sql, map, TieReply.class);
    }
}
