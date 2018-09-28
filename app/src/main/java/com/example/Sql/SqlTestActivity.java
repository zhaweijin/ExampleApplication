package com.example.Sql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.JsonTest.JsonStringTest;
import com.example.carter.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by zwj on 3/19/18.
 */

public class SqlTestActivity extends BaseActivity{

    private String TAG = "SqlTestActivity";

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SqlTestActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /*MyBean bean1 = new MyBean();
        bean1.setName("cater");
        MyDataDao.getInstance(this).insert(bean1);

        MyBean bean2 = new MyBean();
        bean2.setName("xdssdf");
        MyDataDao.getInstance(this).insert(bean2);*/



        long length = MyDataDao.getInstance(this).queryCount();
        Logger.v("length=="+length);


        List<MyBean> list = MyDataDao.getInstance(this).queryAll();
        for (int i = 0; i < list.size(); i++) {
            Logger.v("name="+list.get(i).getName());
        }


        List<MyBean> bean = MyDataDao.getInstance(this).queryName("xdssdf");
        if(bean!=null){
            Logger.v("search xdssdf size="+bean.size());
        }




    }
}
