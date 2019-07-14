package com.dao;

import com.common.ConnSQL;
import com.model.BarFollow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarFollowDao {
    private Connection conn;
    public BarFollowDao(){
        conn = ConnSQL.getConn();
    }

    private BarFollow querySingle(PreparedStatement pst) throws SQLException {
        BarFollow bf = new BarFollow();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            bf.setUID(rs.getInt("UID"));
            bf.setBarID(rs.getInt("BarID"));
            bf.setBarName(rs.getString("BarName"));
        }
        rs.close();
        pst.close();
        return bf;
    }
    private List<BarFollow> queryList(PreparedStatement pst) throws SQLException {
        List<BarFollow> lis = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            BarFollow bf = new BarFollow();
            bf.setUID(rs.getInt("UID"));
            bf.setBarID(rs.getInt("BarID"));
            bf.setBarName(rs.getString("BarName"));
            lis.add(bf);
        }
        rs.close();
        pst.close();
        return lis;
    }

    public List<BarFollow> findFollowBars(int UID) throws SQLException {
        String sql = "SELECT * FROM barfollow WHERE UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,UID);
        return queryList(pst);
    }
    public BarFollow isFollowBar(int UID, int barID) throws SQLException {
        String sql = "SELECT * FROM barfollow WHERE BarID=? AND UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,barID);
        pst.setInt(2,UID);
        return querySingle(pst);
    }

    public int followBar(int UID, int barID, String barName) throws SQLException {
        String sql = "INSERT INTO barfollow(UID, BarID, BarName) VALUES (?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,UID);
        pst.setInt(2,barID);
        pst.setString(3,barName);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }
    public int unfollowBar(int UID, int barID) throws SQLException {
        String sql = "DELETE FROM barfollow WHERE UID=? AND BarID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,UID);
        pst.setInt(2,barID);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }
}