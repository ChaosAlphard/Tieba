package com.dao;

import com.common.ConnSQL;
import com.common.SQLHandler;
import com.tools.TimeTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class NewTie {
    public int CreateNewTie(int barID,String tieTitle,String tieMain,String tieUser,int tieUserID) {
        String time = TimeTool.getCurrentTime();
        String sql = "INSERT INTO ties(BarID, TieTitle, TieMain, TieUser, TieUserID, PostTime, UpdateTime, Visible, Elite)" +
                " VALUES(?,?,?,?,?,?,?,1,0)";

        Map<Integer, Object> map = new HashMap<>();
        map.put(1,barID);
        map.put(2,tieTitle);
        map.put(3,tieMain);
        map.put(4,tieUser);
        map.put(5,tieUserID);
        map.put(6,time);
        map.put(7,time);

        return SQLHandler.update(sql,map);
    }

    public synchronized int CreateNewReply(int tieID,String reply,String reUser,int reUserID) {
        ConnSQL conn = new ConnSQL(false);
        Connection connection = conn.getConn();

        try {
            // Step1: 获取楼层计数, +1加上主楼, 再+1即为插入的楼层
            String step1SQL = "SELECT count(Floor) FROM tiereply WHERE TieID=?";
            PreparedStatement step1PST = connection.prepareStatement(step1SQL);
            step1PST.setInt(1,tieID);
            ResultSet rs = step1PST.executeQuery();

            int count = 0;
            if(rs.next()) {
                count = rs.getInt(1) + 2;
                rs.close();
            } else {
                rs.close();
                error("获取帖子楼层信息失败");
                return 0;
            }
            step1PST.close();
            // End

            String time = TimeTool.getCurrentTime();

            // Step2 根据Step1 查到的楼层, 插入新的回复
            String step2SQL = "INSERT INTO tiereply(TieID, Floor, Reply, ReUser, ReUserID, ReTime, Visible)" +
                    " VALUES(?,?,?,?,?,?,1)";
            PreparedStatement step2PST = connection.prepareStatement(step2SQL);
            step2PST.setInt(1,tieID);
            step2PST.setInt(2,count);
            step2PST.setString(3,reply);
            step2PST.setString(4,reUser);
            step2PST.setInt(5,reUserID);
            step2PST.setString(6,time);

            int step2Count = step2PST.executeUpdate();
            if(step2Count < 1) {
                error("插入失败");
                connection.rollback();
                return -1;
            }
            // End

            // Step3 更新帖子的回复时间
            String step3SQL = "UPDATE ties SET UpdateTime=? WHERE TieID=?";
            PreparedStatement step3PST = connection.prepareStatement(step3SQL);

            step3PST.setString(1,time);
            step3PST.setInt(2,tieID);

            int step3Count = step2PST.executeUpdate();
            if(step3Count < 1) {
                error("更新失败");
                connection.rollback();
                return -1;
            }

            connection.commit();
            step2PST.close();
            step3PST.close();

            return 2;
        } catch(SQLException e) {
            error("回复失败");
            try {
                connection.rollback();
            } catch(SQLException ex) {
                error("====================回滚失败====================");
                ex.printStackTrace();
            }
            e.printStackTrace();
            return -1;
        } finally {
            conn.close();
        }
    }

    private static void error(Object o) {
        System.out.println("NewTie [ Error ]: "+o);
    }
}
