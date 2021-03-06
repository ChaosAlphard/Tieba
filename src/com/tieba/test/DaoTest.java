package com.tieba.test;

import com.tieba.dao.BarDao;
import com.tieba.dao.UserDao;
import com.tieba.model.Bar;
import com.tieba.model.User;

import java.util.List;
import java.util.Optional;

public class DaoTest {
    public static void main(String[] args){
        DaoTest test = new DaoTest();
        test.isBarExist("Java");
//        for(int i=0; i<20; i++) {
//            new Thread(() -> test.findByName("a")).start();
//        }
    }

    private void findColumn(String column, Object value) {
        UserDao dao = new UserDao();
        Optional<User> user = Optional.ofNullable(dao.FindSingle(column,value));
        System.out.println(user);
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
