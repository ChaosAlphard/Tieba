package com.dao;

import com.common.ConnSQL;
import com.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection conn;
    public UserDao(){
        conn = ConnSQL.getConn();
    }

    private void setUser(User u, ResultSet rs) throws SQLException {
        while(rs.next()){
            u.setUID(rs.getInt("UID"));
            u.setAccount(rs.getString("Account"));
            u.setUsername(rs.getString("Username"));
            u.setPassword(rs.getString("Password"));
            u.setAdminLv(rs.getInt("Adminlv"));
        }
    }
    private void setUserList(List<User> lis, ResultSet rs) throws SQLException {
        while(rs.next()){
            User u = new User();
            u.setUID(rs.getInt("UID"));
            u.setAccount(rs.getString("Account"));
            u.setUsername(rs.getString("Username"));
            u.setPassword(rs.getString("Password"));
            u.setAdminLv(rs.getInt("Adminlv"));
            lis.add(u);
        }
    }
////////////////////////////////////////////////////////////////////////////////
//Find方法///////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public User FindSingle(String column, String value) throws SQLException {
        String sql="SELECT * FROM user WHERE "+column+"='"+value+"' LIMIT 1";
        User u = new User();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        setUser(u,rs);
        rs.close();
        st.close();

        return u;
    }
    public List<User> FindMultiple(String column, int value) throws SQLException {
        String sql="SELECT * FROM user WHERE "+column+"='"+value+"'";
        List<User> lis = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        setUserList(lis,rs);
        rs.close();
        st.close();

        return lis;
    }
    public List<User> FindNameLike(String name) throws SQLException {
        String sql="SELECT * FROM user WHERE Username LIKE ?";
        List<User> lis = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,"%"+name+"%");
        ResultSet rs = pst.executeQuery();
        setUserList(lis,rs);
        System.out.println("UserDao: FindNameLike>>>>\n"+pst.toString());
        rs.close();
        pst.close();

        return lis;
    }
    public User findByID(int uid) throws SQLException {
        String sql = "SELECT * FROM user WHERE UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,uid);
        ResultSet rs = pst.executeQuery();

        User u = new User();
        setUser(u,rs);
        rs.close();
        pst.close();

        return u;
    }

    public int bannedUser(int uid) throws SQLException {
        String sql = "UPDATE user SET Adminlv=0 WHERE UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,uid);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }
    public int unbannedUser(int uid) throws SQLException {
        String sql = "UPDATE user SET Adminlv=1 WHERE UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,uid);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }
    public int setAsAdmin(int uid) throws SQLException {
        String sql = "UPDATE user SET Adminlv=2 WHERE UID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,uid);
        int i = pst.executeUpdate();
        pst.close();
        return i;
    }


////////////////////////////////////////////////////////////////////////////////
    //登陆注册
    public String[] Login(String acont, String pwd) throws SQLException {
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
    public int Register(String aot, String usr, String pwd) throws SQLException {
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
    public String[] AdminLogin(String aot, String pwd) throws SQLException {
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