package com.example.Sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwj on 3/19/18.
 */

public interface MyDataImpl {
    void insert(ArrayList<MyBean> beanArrayList);

    void insert(MyBean myBean);

    void update(String name, String price);

    void update2(String columnName, String columnValue);

    void update3(String queryColumnName, String queryColumnValue, String setColumnName, String setColumnValue);


    void delete(String name);

    int deleteAll();


    ArrayList<String> queryPrice(String name);

    String queryAuthor(String name, String price);

    long queryCount();

    ArrayList<MyBean> queryId(int id);

    ArrayList<MyBean> queryAll();

    List<MyBean> queryName(String name);

}
