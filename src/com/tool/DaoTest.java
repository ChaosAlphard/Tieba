package com.tool;

import com.common.ConnSQL;
import com.dao.*;
import com.model.*;

import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTest {
    public void user() throws SQLException {
        System.out.println("User==============================");
        UserDao dao = new UserDao();
        List<User> lis=dao.FindNameLike("i");
        for (User li : lis) {
            System.out.println(li.getUsername());
        }
        //pst对象使用setString为 where ? = ? 赋值时,由于会自动加单引号
        //导致变为 where 'uid'='1' , 从而导致查询失败
    }

    public static void main(String[] args){
        DaoTest d = new DaoTest();
        TieDao td = new TieDao();
        UserDao udao = new UserDao();
        BarFollowDao bfdao = new BarFollowDao();
        //String url="!@#$%^&*()-+*/=_,.<>/?￥＄，。？、）（【】{}|{}[]你好asds15452";
        //System.out.println(url);

        try {
            //d.user();
            //String a=URLEncoder.encode(url,"UTF-8");
            //System.out.println(a);
            //String b= URLDecoder.decode(a,"UTF-8");
            //System.out.println(b);
            //List<Tie> lis = td.FindByPage(4,3,2);
            //for (Tie li : lis) {
            //    System.out.println("li = " + li.getTieTitle()+"\t"+li.getTieMain()+"\t"+li.getTieID());
            //}
            //List<TieReply> tlis = new TieReplyDao().FindByTieID(4);
            //for (TieReply li : tlis) {
            //    System.out.println(li.getFloor());
            //    System.out.println(li.getTieID());
            //    System.out.println(li.getReply());
            //}
            //
            //lis = td.findRecentlyTie();
            //for (Tie li : lis) {
            //    System.out.println(li.getBarID());
            //    System.out.println(li.getTieTitle());
            //    System.out.println(li.getTieMain());
            //}
            List<BarFollow> lis = bfdao.findFollowBars(1);
            System.out.println(lis.isEmpty());
            for (BarFollow li : lis) {
                System.out.println(li.getUID());
                System.out.println(li.getBarID());
                System.out.println(li.getBarName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnSQL.closeSQL();
        }
    }
}
