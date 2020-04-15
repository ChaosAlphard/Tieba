package com.test;

import com.dao.BarDao;
import com.dao.UserDao;
import com.model.Bar;
import com.model.User;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DaoTest {
    public static void main(String[] args){
        DaoTest test = new DaoTest();
//        test.isBarExist("Java");
        for(int i=0; i<20; i++) {
            new Thread(() -> test.findByName("a")).start();
        }
    }

    private void isBarExist(String name) {
        BarDao dao = new BarDao();
        System.out.println(dao.isBarExist(name));
    }

    private void findByName(String name) {
        BarDao dao = new BarDao();
        final List<Bar> lis = dao.FindBars(name);
        lis.forEach(it -> System.out.println(it.toString()));
    }

    private void checkUserLv(int id) {
        UserDao dao = new UserDao();
        final User byID = dao.findByID(id);
        System.out.println(byID.toString());
        System.out.println(dao.checkUserLV(id));
    }
}
