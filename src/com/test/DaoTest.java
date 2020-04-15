package com.test;

import com.common.ConnSQL;
import com.dao.*;
import com.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoTest {
//    public void user() {
//        System.out.println("User==============================");
//        UserDao dao = new UserDao();
//        User u=dao.FindSingle("account", "wan147158");
//        System.out.println(u.toString());
//        System.out.println(u.hashCode());
//    }

    private void findBarByLike() {
        BarDao dao = new BarDao();
        List<Bar> list = dao.FindBars("ECMA");
        list.forEach( it -> System.out.println(it.toString()));
    }

    public static void main(String[] args){
        DaoTest test = new DaoTest();
//        test.findBarByLike();
        for(int i=0; i<20; i++) {new Thread(test::findBarByLike).start();}
//        test.user();

        //TieDao td = new TieDao();
        //UserDao udao = new UserDao();
        //BarFollowDao bfdao = new BarFollowDao();
        //String url="!@#$%^&*()-+*/=_,.<>/?￥＄，。？、）（【】{}|{}[]你好asds15452";
        //System.out.println(url);

        /*
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
        }
        */
    }
}
