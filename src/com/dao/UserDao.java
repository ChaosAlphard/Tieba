package com.dao;

import com.common.SQLHandler;
import com.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    public User FindSingle(String column, String value) {
        //pst对象使用setString为 where ? = ? 赋值时,由于会自动加单引号
        //导致变为 where 'uid'='1' , 从而导致查询失败
        //所以需要用concat来去掉单引号
        String sql="SELECT * FROM user WHERE concat(?)=? limit 1";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, column);
        map.put(2, value);

        return SQLHandler.querySingle(sql, map, User.class);
    }

    public List<User> FindMultiple(String column, int value) {
        String sql="SELECT * FROM user WHERE concat(?)=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, column);
        map.put(2, value);

        return SQLHandler.queryMultiple(sql, map, User.class);
    }
////////////////////////////////////////////////////////////////////////////////
//Find方法///////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public List<User> FindNameLike(String name) {
        String sql="SELECT * FROM user WHERE Username LIKE ?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, '%'+name+'%');

        return SQLHandler.queryMultiple(sql, map, User.class);
    }
    public User findByID(int uid) {
        String sql = "SELECT * FROM user WHERE UID=? limit 1";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, uid);

        return SQLHandler.querySingle(sql, map, User.class);
    }

    public int bannedUser(int uid) {
        String sql = "UPDATE user SET Adminlv=0 WHERE UID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, uid);

        return SQLHandler.update(sql, map);
    }
    public int unbannedUser(int uid) {
        String sql = "UPDATE user SET Adminlv=1 WHERE UID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, uid);

        return SQLHandler.update(sql, map);
    }
    public int setAsAdmin(int uid) {
        String sql = "UPDATE user SET Adminlv=2 WHERE UID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, uid);

        return SQLHandler.update(sql, map);
    }

    public boolean checkUserLV(int uid) {
        String sql = "SELECT Adminlv FROM user WHERE UID=?";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, uid);

        final Integer res = SQLHandler.queryColumn(sql, map, Integer.class);

        return (res != null && res != 0);
    }

////////////////////////////////////////////////////////////////////////////////
    //登陆注册
    public String[] Login(String aot, String pwd) {
        String sql="select UID,Username,Adminlv from user where Account=? and Password=? limit 1";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, aot);
        map.put(2, pwd);

        User user = SQLHandler.querySingle(sql, map, User.class);

        String[] userinfo = new String[3];
        if(user == null) {
            return userinfo;
        }
        userinfo[0]=String.valueOf(user.getUID());
        userinfo[1]=user.getUsername();
        userinfo[2]=String.valueOf(user.getAdminLv());

        return userinfo;
    }
    public int Register(String aot, String usr, String pwd) {
        String sql="INSERT INTO user (Account, Username, Password, Adminlv) VALUES (?,?,?,1)";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, aot);
        map.put(2, usr);
        map.put(3, pwd);

        return SQLHandler.update(sql, map);
    }
    public String[] AdminLogin(String aot, String pwd) {
        String sql = "SELECT UID,Username,Adminlv FROM user WHERE Adminlv>1 AND Account=? AND Password=? limit 1";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, aot);
        map.put(2, pwd);

        User user = SQLHandler.querySingle(sql, map, User.class);

        String[] info = new String[3];
        if(user == null) {
            return info;
        }
        info[0]=String.valueOf(user.getUID());
        info[1]=user.getUsername();
        info[2]=String.valueOf(user.getAdminLv());

        return info;
    }
}