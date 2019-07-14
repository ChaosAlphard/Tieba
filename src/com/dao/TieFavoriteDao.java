package com.dao;

import com.common.ConnSQL;
import com.model.TieFavorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TieFavoriteDao {
    private Connection conn;
    public TieFavoriteDao(){
        conn = ConnSQL.getConn();
    }

    private TieFavorite querySingle(PreparedStatement pst) throws SQLException {
        TieFavorite tf = new TieFavorite();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            tf.setUID(rs.getInt("UID"));
            tf.setTieID(rs.getInt("TieID"));
            tf.setTieTitle(rs.getString("TieTitle"));
        }
        rs.close();
        pst.close();
        return tf;
    }
    private List<TieFavorite> queryList(PreparedStatement pst) throws SQLException {
        List<TieFavorite> lis = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            TieFavorite tf = new TieFavorite();
            tf.setUID(rs.getInt("UID"));
            tf.setTieID(rs.getInt("TieID"));
            tf.setTieTitle(rs.getString("TieTitle"));
            lis.add(tf);
        }
        rs.close();
        pst.close();
        return lis;
    }

    public List<TieFavorite> findFavoriteTies(int UID) throws SQLException {
        String sql = "SELECT * FROM tiefavorite WHERE UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,UID);
        return queryList(pst);
    }
    public TieFavorite isFavoriteTie(int UID,int tieID) throws SQLException {
        String sql = "SELECT * FROM tiefavorite WHERE TieID=? AND UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,tieID);
        pst.setInt(2,UID);
        return querySingle(pst);
    }

    public int favoriteTie(int UID,int tieID,String tieTitle) throws SQLException {
        String sql = "INSERT INTO tiefavorite(UID, TieID, TieTitle) VALUES (?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,UID);
        pst.setInt(2,tieID);
        pst.setString(3,tieTitle);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }
    public int unfavoriteTie(int UID,int tieID) throws SQLException {
        String sql = "DELETE FROM tiefavorite WHERE UID=? AND TieID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,UID);
        pst.setInt(2,tieID);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }
}