package com.dao;

import com.common.ConnSQL;
import com.model.Bar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarDao {
    private Connection conn;
    public BarDao(){
        conn = ConnSQL.getConn();
    }

    private void setBar(Bar b, ResultSet rs) throws SQLException {
        while(rs.next()){
            b.setBarID(rs.getInt("BarID"));
            b.setBarName(rs.getString("BarName"));
            b.setBarContent(rs.getString("BarContent"));
            b.setVisible(rs.getInt("Visible"));
        }
    }
    private void setBarList(List<Bar> lis, ResultSet rs) throws SQLException {
        while(rs.next()){
            Bar b = new Bar();
            b.setBarID(rs.getInt("BarID"));
            b.setBarName(rs.getString("BarName"));
            b.setBarContent(rs.getString("BarContent"));
            b.setVisible(rs.getInt("Visible"));
            lis.add(b);
        }
    }

    public List<Bar> FindBars(String bars) throws SQLException {
        String sql = "SELECT * FROM bars WHERE BarName like ? AND Visible=1";
        List<Bar> lis = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,"%"+bars+"%");
        ResultSet rs = pst.executeQuery();
        setBarList(lis,rs);
        System.out.println("BarDao: FindBars>>>>\n"+pst.toString());
        rs.close();
        pst.close();
        return lis;
    }
    public Bar FindById(int barID) throws SQLException {
        String sql = "SELECT * FROM bars WHERE BarID=?";
        Bar b = new Bar();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,barID);
        ResultSet rs = pst.executeQuery();
        setBar(b,rs);
        System.out.println("BarDao: FindById>>>>\n"+pst.toString());
        rs.close();
        pst.close();
        return b;
    }
    public String findByName(String barName) throws SQLException {
        String sql="SELECT BarName FROM bars WHERE BarName=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,barName);
        ResultSet rs = pst.executeQuery();
        String name=null;
        while(rs.next()){
            name=rs.getString("BarName");
        }
        return name;
    }

    public int createNewBar(String barName,String barContent) throws SQLException {
        String sql="INSERT INTO bars(BarName, BarContent, Visible) VALUES(?,?,1)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,barName);
        pst.setString(2,barContent);
        int i=pst.executeUpdate();
        System.out.println("BarDao: CreateNewBar>>>>\n"+pst.toString());
        pst.close();
        return i;
    }
}
