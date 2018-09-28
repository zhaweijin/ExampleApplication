package com.example.carter;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by carter on 2/28/17.
 */
public class ExampleApplication extends Application{



    private static ExampleApplication sInstance;



    public static ExampleApplication getsInstance() {
        return sInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        this.sInstance = this;
        /* Bugly SDK初始化
        * 参数1：上下文对象
        * 参数2：APPID，平台注册时得到,注意替换成你的appId
        * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
        */
//        CrashReport.initCrashReport(getApplicationContext(), "4e49840392", true);

        /**
         * Stetho初始化
         */
        Stetho.initialize(Stetho.newInitializerBuilder(this)
              .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
              .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
              .build());

        /**
         * 数据库初始化
         */
//        ActiveAndroid.initialize(this);

        //初始化Fresco图片加载框架
//        Fresco.initialize(this);

        Logger.init("carter")  //tag 默认PRETTYLOGGER
                .setLogLevel(LogLevel.FULL); //LogLevel.NONE 就是不打印输出


    }


    @Override
    public void onTerminate() {
        super.onTerminate();
//        ActiveAndroid.dispose();
    }


    @Override
    public void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }



}
