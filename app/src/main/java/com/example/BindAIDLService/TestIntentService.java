package com.example.BindAIDLService;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zwj on 3/15/18.
 */

public class TestIntentService extends IntentService{

    public String tag = "TestIntentService";

    /**
     * 必须要定义构造函数
     */
    public TestIntentService(){
        super("TestIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

         try {
             Log.v(tag,"start....");
             Thread.sleep(2000);
             Log.v(tag,"start....2");
             Thread.sleep(2000);
             Log.v(tag,"start....3");
         } catch (Exception e) {
             e.printStackTrace();
         }


    }
}
