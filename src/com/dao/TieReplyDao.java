package com.dao;

import com.common.ConnSQL;
import com.model.TieReply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TieReplyDao {
    private Connection conn;
    public TieReplyDao(){
        conn= ConnSQL.getConn();
    }

    private void setTieReplayList(List<TieReply> lis,ResultSet rs) throws SQLException {
        while(rs.next()){
            TieReply tr = new TieReply();
            tr.setTieID(rs.getInt("TieID"));
            tr.setFloor(rs.getInt("Floor"));
            tr.setReply(rs.getString("Reply"));
            tr.setReUser(rs.getString("ReUser"));
            tr.setReUserID(rs.getInt("ReUserID"));

            String rt = rs.getString("ReTime");
            tr.setReTime(rt.substring(0,rt.indexOf(".")));

            tr.setVisible(rs.getInt("Visible"));
            lis.add(tr);
        }
    }

    public List<TieReply> FindByTieID(int tieID) throws SQLException {
        String sql = "SELECT * FROM tiereply WHERE TieID=? AND Visible=1";
        List<TieReply> lis = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,tieID);
        ResultSet rs = pst.executeQuery();
        setTieReplayList(lis,rs);
        rs.close();
        pst.close();
        return lis;
    }
}
