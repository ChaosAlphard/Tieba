package com.test;

import com.common.ConnSQL;
import com.dao.*;
import com.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoTest {
    public static void main(String[] args){
        DaoTest test = new DaoTest();
        test.isBarExist("Java");
    }

    private void isBarExist(String name) {
        BarDao dao = new BarDao();
        System.out.println(dao.isBarExist(name));
    }
}
