package com.dao;

import com.common.ConnSQL;
import com.model.User;
import com.tools.DBTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public User FindSingle(String column, String value) {
        //pst对象使用setString为 where ? = ? 赋值时,由于会自动加单引号
        //导致变为 where 'uid'='1' , 从而导致查询失败
        //所以需要用concat来去掉单引号
        String sql="SELECT * FROM user WHERE concat(?)=? limit 1";
        User user = new User();
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setString(1,column);
            pst.setString(2,value);
            final ResultSet rs = pst.executeQuery();
            user = DBTool.setData(rs, User.class);
            System.out.println(pst.toString());
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> FindMultiple(String column, int value) {
        String sql="SELECT * FROM user WHERE concat(?)=?";
        List<User> lis = new ArrayList<>();
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setString(1,column);
            pst.setInt(2,value);
            final ResultSet rs = pst.executeQuery();

            lis = DBTool.setDataList(rs, User.class);

            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return lis;
    }
////////////////////////////////////////////////////////////////////////////////
//Find方法///////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public List<User> FindNameLike(String name) {
        String sql="SELECT * FROM user WHERE Username LIKE ?";
        List<User> lis = new ArrayList<>();
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setString(1,"%"+name+"%");
            final ResultSet rs = pst.executeQuery();

            lis = DBTool.setDataList(rs, User.class);

            System.out.println("UserDao: FindNameLike>>>>\n"+pst.toString());
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return lis;
    }
    public User findByID(int uid) {
        String sql = "SELECT * FROM user WHERE UID=?";
        User user = null;
        try(ConnSQL conn = new ConnSQL();
            PreparedStatement pst = conn.getConn(true).prepareStatement(sql)) {

            pst.setInt(1,uid);
            ResultSet rs = pst.executeQuery();

            user = DBTool.setData(rs, User.class);
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public int bannedUser(int uid) {
        String sql = "UPDATE user SET Adminlv=0 WHERE UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,uid);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }
    public int unbannedUser(int uid) {
        String sql = "UPDATE user SET Adminlv=1 WHERE UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,uid);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }
    public int setAsAdmin(int uid) {
        String sql = "UPDATE user SET Adminlv=2 WHERE UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,uid);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }

    public boolean checkUserLV(int uid) {
        String sql = "SELECT Adminlv FROM user WHERE UID=?";
        int lv=0;
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,uid);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            lv=rs.getInt(1);
        }
        rs.close();
        pst.close();
        Boolean res=false;
        if(lv!=0){
            res = true;
        }
        return res;
    }

////////////////////////////////////////////////////////////////////////////////
    //登陆注册
    public String[] Login(String acont, String pwd) {
        String sql="select UID,Username,Adminlv from user where Account=? and Password=?";
        String[] userinfo = new String[3];
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,acont);
        pst.setString(2,pwd);
        //Statement st=conn.createStatement();
        ResultSet rs=pst.executeQuery();
        while(rs.next()) {
            userinfo[0]=String.valueOf(rs.getInt("UID"));
            userinfo[1]=rs.getString("Username");
            userinfo[2]=String.valueOf(rs.getInt("Adminlv"));
        }
        System.out.println("UserDao: Login>>>>\n"+pst.toString());
        rs.close();
        pst.close();

        return userinfo;
    }
    public int Register(String aot, String usr, String pwd) {
        String sql="INSERT INTO user (Account, Username, Password, Adminlv)"+
                  " VALUES (?,?,?,1)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,aot);
        pst.setString(2,usr);
        pst.setString(3,pwd);
        int rs=pst.executeUpdate();
        System.out.println("UserDao: Register>>>>\n"+pst.toString());
        pst.close();

        return rs;
    }
    public String[] AdminLogin(String aot, String pwd) {
        String sql = "SELECT UID,Username,Adminlv FROM user WHERE Adminlv>1 AND Account=? AND Password=?";
        String[] info = new String[3];
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,aot);
        pst.setString(2,pwd);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            info[0]=String.valueOf(rs.getInt("UID"));
            info[1]=rs.getString("Username");
            info[2]=String.valueOf(rs.getInt("Adminlv"));
        }
        System.out.println("UserDao: Admin>>>>\n"+pst.toString());
        rs.close();
        pst.close();

        return info;
    }
}