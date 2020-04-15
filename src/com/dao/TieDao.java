package com.dao;

import com.common.ConnSQL;
import com.model.Tie;
import com.tools.DBTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TieDao {
    public Tie FindByID(int tieID) {
        String sql = "SELECT * FROM ties WHERE TieID=?";
        Tie tie = null;

        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {
            pst.setInt(1,tieID);
            final ResultSet rs = pst.executeQuery();

            tie = DBTool.setData(rs, Tie.class);

            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return tie;
    }

    public List<Tie> FindByBarID(int barID) {
        String sql = "SELECT * FROM ties WHERE BarID=? AND Visible=1 ORDER BY UpdateTime DESC";
        List<Tie> lis = new ArrayList<>();

        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {
            pst.setInt(1,barID);
            final ResultSet rs = pst.executeQuery();

            lis = DBTool.setDataList(rs,Tie.class);

            System.out.println("TieDao: FindTiesByBarID>>>>\n"+pst.toString());
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return lis;
    }

    public List<Tie> findEliteByBarID(int barID) {
        String sql = "SELECT * FROM ties WHERE Elite=1 AND BarID=? AND Visible=1 ORDER BY UpdateTime DESC";
        List<Tie> lis = new ArrayList<>();

        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setInt(1,barID);
            final ResultSet rs = pst.executeQuery();

            lis = DBTool.setDataList(rs, Tie.class);

            System.out.println("TieDao: findEliteByBarID>>>>\n"+pst.toString());
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return lis;
    }

    public List<Tie> findDeletedTie(String tieUser) {
        String sql = "SELECT * FROM ties WHERE Visible=0 AND TieUser=? ORDER BY PostTime DESC";
        List<Tie> lis = new ArrayList<>();

        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setString(1,tieUser);
            final ResultSet rs = pst.executeQuery();

            lis = DBTool.setDataList(rs, Tie.class);

            System.out.println("TieDao: findDeletedTie>>>>\n"+pst.toString());
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return lis;
    }

    public List<Tie> FindByPage(int barID, int pageStart, int pageAmount) {
        String sql = "SELECT * from ties WHERE BarID=? AND TieID>=? LIMIT ?";
        List<Tie> lis = new ArrayList<>();

        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setInt(1,barID);
            pst.setInt(2,(pageStart-1)*pageAmount+1);
            pst.setInt(3,pageAmount);
            final ResultSet rs = pst.executeQuery();

            lis = DBTool.setDataList(rs, Tie.class);

            System.out.println("TieDao: FindByPage>>>>\n"+pst.toString());
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return lis;
    }

    public List<Tie> findRecentlyTie() {
        String sql = "SELECT * FROM ties WHERE Visible=1 ORDER BY UpdateTime DESC LIMIT 10";
        List<Tie> lis = new ArrayList<>();
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            final ResultSet rs = pst.executeQuery();

            lis = DBTool.setDataList(rs, Tie.class);

            System.out.println("TieDao: recentlyTie>>>>\n"+pst.toString());
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return lis;
    }

    public int deleteTie(int tieID) {
        String sql = "UPDATE ties SET Visible=0 WHERE TieID=?";
        int i= 0;
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setInt(1,tieID);
            i = pst.executeUpdate();

            System.out.println("deleteTie:\n"+pst.toString());
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int restoreTie(int tieID) {
        String sql = "UPDATE ties SET Visible=1 WHERE TieID=?";
        int i= 0;
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setInt(1,tieID);
            i = pst.executeUpdate();

            System.out.println("restoreTie:\n"+pst.toString());
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int setTieElite(int tieID) {
        String sql = "UPDATE ties SET Elite=1 WHERE TieID=?";
        int i= 0;
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setInt(1,tieID);
            i = pst.executeUpdate();

            System.out.println("setTieElite:\n"+pst.toString());
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int unsetTieElite(int tieID) {
        String sql = "UPDATE ties SET Elite=0 WHERE TieID=?";
        int i= 0;
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setInt(1,tieID);
            i = pst.executeUpdate();

            System.out.println("unsetTieElite:\n"+pst.toString());
        } catch(SQLException e) {
            e.printStackTrace();
        }
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
