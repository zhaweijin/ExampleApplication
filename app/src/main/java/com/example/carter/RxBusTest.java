package com.example.carter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.UnitExample.UnitActivity;
import com.example.common.RxBus;


import rx.Observable;
import rx.functions.Action1;

/**
 * Created by carter on 2/23/17.
 */
public class RxBusTest extends Activity {

    private String tag = RxBusTest.class.getSimpleName();
    private Observable<String> ob;
    private final String obtag = "obflag";

    private Observable<User> obUser;
    private final String usertag = "usertag";


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RxBusTest.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxbus_test_main);

        //创建被观察者
        ob = RxBus.get().register(obtag,String.class);
        ob.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.v(tag,"s===="+s);
            }
        });


        obUser = RxBus.get().register(usertag,User.class);
        obUser.subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
               Log.v(tag,"user=="+user.getAge());
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    //发送内容
                    RxBus.get().post(obtag,"my name is carter");
                    Thread.sleep(3000);
                    RxBus.get().post(obtag,"my name is alina");
                    Thread.sleep(3000);

                    User user = new User();
                    user.setAge("33");
                    user.setName("tttttt");
                    RxBus.get().post(usertag,user);
                }catch (Exception e){
                    e.printStackTrace();;
                }
            }
        }).start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(tag,ob);
    }

    public class User {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        private String name;
        private String age;


    }
}
