package com.dao;

import com.common.ConnSQL;
import com.model.Tie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TieDao {
    private Connection conn;
    public TieDao(){
        conn = ConnSQL.getConn();
    }

    private void setTie(Tie t, ResultSet rs) throws SQLException {
        while(rs.next()){
            t.setTieID(rs.getInt("TieID"));
            t.setBarID(rs.getInt("BarID"));
            t.setTieTitle(rs.getString("TieTitle"));
            t.setTieMain(rs.getString("TieMain"));
            t.setTieUser(rs.getString("TieUser"));
            t.setTieUserID(rs.getInt("TieUserID"));

            //getString获取时间后面会多出".0"
            //xxxx-xx-xx xx:xx:xx.0去掉".0"
            String pt = rs.getString("PostTime");
            t.setPostTime(pt.substring(0,pt.indexOf(".")));

            String ut = rs.getString("UpdateTime");
            t.setUpdateTime(ut.substring(0,ut.indexOf(".")));

            t.setVisible(rs.getInt("Visible"));
            t.setElite(rs.getInt("Elite"));
        }
    }
    private void setTieList(List<Tie> lis, ResultSet rs) throws SQLException {
        while(rs.next()){
            Tie t = new Tie();
            t.setTieID(rs.getInt("TieID"));
            t.setBarID(rs.getInt("BarID"));
            t.setTieTitle(rs.getString("TieTitle"));
            t.setTieMain(rs.getString("TieMain"));
            t.setTieUser(rs.getString("TieUser"));
            t.setTieUserID(rs.getInt("TieUserID"));

            String pt = rs.getString("PostTime");
            t.setPostTime(pt.substring(0,pt.indexOf(".")));

            String ut = rs.getString("UpdateTime");
            t.setUpdateTime(ut.substring(0,ut.indexOf(".")));

            t.setVisible(rs.getInt("Visible"));
            t.setElite(rs.getInt("Elite"));
            lis.add(t);
        }
    }

    public Tie FindByID(int tieID) throws SQLException {
        String sql = "SELECT * FROM ties WHERE TieID=?";
        Tie t = new Tie();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,tieID);
        ResultSet rs = pst.executeQuery();
        setTie(t,rs);
        rs.close();
        pst.close();
        return t;
    }

    public List<Tie> FindByBarID(int barID) throws SQLException {
        String sql = "SELECT * FROM ties WHERE BarID=? AND Visible=1 ORDER BY UpdateTime DESC";
        List<Tie> lis = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,barID);
        ResultSet rs = pst.executeQuery();
        setTieList(lis,rs);
        System.out.println("TieDao: FindTiesByBarID>>>>\n"+pst.toString());
        rs.close();
        pst.close();
        return lis;
    }

    public List<Tie> findEliteByBarID(int barID) throws SQLException {
        String sql = "SELECT * FROM ties WHERE Elite=1 AND BarID=? AND Visible=1 ORDER BY UpdateTime DESC";
        List<Tie> lis = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,barID);
        ResultSet rs = pst.executeQuery();
        setTieList(lis,rs);
        System.out.println("TieDao: findEliteByBarID>>>>\n"+pst.toString());
        rs.close();
        pst.close();
        return lis;
    }

    public List<Tie> findDeletedTie(String tieUser) throws SQLException {
        String sql = "SELECT * FROM ties WHERE Visible=0 AND TieUser=? ORDER BY PostTime DESC";
        List<Tie> lis = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,tieUser);
        ResultSet rs = pst.executeQuery();
        setTieList(lis,rs);
        System.out.println("TieDao: findDeletedTie>>>>\n"+pst.toString());
        rs.close();
        pst.close();
        return lis;
    }

    public List<Tie> FindByPage(int barID, int pageStart, int pageAmount) throws SQLException {
        String sql = "SELECT * from ties WHERE BarID=? AND TieID>=? LIMIT ?";
        List<Tie> lis = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,barID);
        pst.setInt(2,(pageStart-1)*pageAmount+1);
        pst.setInt(3,pageAmount);
        ResultSet rs = pst.executeQuery();
        setTieList(lis,rs);
        System.out.println("TieDao: FindByPage>>>>\n"+pst.toString());
        rs.close();
        pst.close();
        return lis;
    }

    public List<Tie> findRecentlyTie() throws SQLException {
        String sql = "SELECT * FROM ties WHERE Visible=1 ORDER BY UpdateTime DESC LIMIT 10";
        List<Tie> lis = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        setTieList(lis,rs);
        System.out.println("TieDao: recentlyTie>>>>\n"+pst.toString());
        rs.close();
        pst.close();
        return lis;
    }

    public int deleteTie(int tieID) throws SQLException {
        String sql = "UPDATE ties SET Visible=0 WHERE TieID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,tieID);
        int i=pst.executeUpdate();
        System.out.println("deleteTie:\n"+pst.toString());
        pst.close();
        return i;
    }

    public int restoreTie(int tieID) throws SQLException {
        String sql = "UPDATE ties SET Visible=1 WHERE TieID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,tieID);
        int i=pst.executeUpdate();
        System.out.println("restoreTie:\n"+pst.toString());
        pst.close();
        return i;
    }

    public int setTieElite(int tieID) throws SQLException {
        String sql = "UPDATE ties SET Elite=1 WHERE TieID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,tieID);
        int i=pst.executeUpdate();
        System.out.println("setTieElite:\n"+pst.toString());
        pst.close();
        return i;
    }

    public int unsetTieElite(int tieID) throws SQLException {
        String sql = "UPDATE ties SET Elite=0 WHERE TieID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,tieID);
        int i=pst.executeUpdate();
        System.out.println("unsetTieElite:\n"+pst.toString());
        pst.close();
        return i;
    }

    //public List<Tie> FindTiesByBarID(int barID) throws SQLException {
    //    String sql = "SELECT * FROM ties,tiereply" +
    //    " WHERE BarID=? AND bars.BarID=ties.BarID" +
    //    " ORDER BY ties.Time DESC LIMIT 0,1";
    //    List<Tie> lis = new ArrayList<>();
    //    PreparedStatement pst = conn.prepareStatement(sql);
    //    pst.setInt(1,barID);
    //    ResultSet rs = pst.executeQuery();
    //    setTieList(lis,rs);
    //    System.out.println("FindTiesByBarID>>>>\n"+pst.toString());
    //    rs.close();
    //    pst.close();
    //    return lis;
    //}
}
