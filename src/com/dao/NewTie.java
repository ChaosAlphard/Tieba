package com.dao;

import com.common.ConnSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTie {
    private Connection conn;
    private Connection naConn;
    public NewTie(){
        conn = ConnSQL.getConn();
    }

    private String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public void SQLRollback(){
        try {
            naConn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeNaSQL(){
        try {
            naConn.close();
            System.out.println("NewTie.closeSQL[success]");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            naConn=null;
        }
    }

    public int CreateNewTie(int barID,String tieTitle,String tieMain,String tieUser,int tieUserID) throws SQLException {
        String time = getCurrentTime();
        String sql = "INSERT INTO ties(BarID, TieTitle, TieMain, TieUser, TieUserID, PostTime, UpdateTime, Visible, Elite)" +
                " VALUES(?,?,?,?,?,?,?,1,0)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,barID);
        pst.setString(2,tieTitle);
        pst.setString(3,tieMain);
        pst.setString(4,tieUser);
        pst.setInt(5,tieUserID);
        pst.setString(6,time);
        pst.setString(7,time);
        int i = pst.executeUpdate();
        System.out.println("NewTie: CreNewTie>>>>\n"+pst.toString());
        pst.close();

        return i;
    }

    public int CreateNewReply(int tieID,String reply,String reUser,int reUserID) throws SQLException {
        naConn = ConnSQL.getNoAutoCommitConn();
        PreparedStatement pst = naConn.prepareStatement("SELECT count(Floor) FROM tiereply WHERE TieID=?");
        pst.setInt(1,tieID);
        ResultSet r = pst.executeQuery();
        int count=0;
        if(r.next()){
            count = r.getInt(1) + 2;
        }

        String time = getCurrentTime();

        String sql = "INSERT INTO tiereply(TieID, Floor, Reply, ReUser, ReUserID, ReTime, Visible)" +
                " VALUES(?,?,?,?,?,?,1)";
        pst = naConn.prepareStatement(sql);
        pst.setInt(1,tieID);
        pst.setInt(2,count);
        pst.setString(3,reply);
        pst.setString(4,reUser);
        pst.setInt(5,reUserID);
        pst.setString(6,time);
        int i = pst.executeUpdate();

        pst = naConn.prepareStatement("UPDATE ties SET UpdateTime=? WHERE TieID=?");
        pst.setString(1,time);
        pst.setInt(2,tieID);
        i += pst.executeUpdate();

        naConn.commit();
        pst.close();

        return i;
    }
}
