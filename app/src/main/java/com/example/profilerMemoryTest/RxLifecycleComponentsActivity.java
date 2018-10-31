package com.example.profilerMemoryTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.RxJava.ObserverTest;
import com.example.carter.BaseActivity;
import com.example.carter.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxLifecycleComponentsActivity extends BaseActivity {

    private Subscription sc;
    private TextView loadingView;
    private LoadingHandler loadingHandler = new LoadingHandler(this);


    /**
     * 静态内部类+WeakReference 方式，防止内存泄漏
     */
    private static class LoadingHandler extends Handler {
        private WeakReference<RxLifecycleComponentsActivity> ref;

        public LoadingHandler(RxLifecycleComponentsActivity activity) {
            ref = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RxLifecycleComponentsActivity activity = ref.get();
            switch (msg.what) {
                case 1:
                    if (null != activity) {
                        removeMessages(1);
                        activity.loadingView.setText(activity.getApplicationContext().getString(R.string.app_name));
                        activity.loadingHandler.sendEmptyMessageDelayed(1, 300);
                    }
                    break;

                default:
                    break;
            }
        }
    }





    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RxLifecycleComponentsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rxlifecycle);


        initData();
    }

    private void initData() {
        Log.v("test","initData........");
        // 每隔1s执行一次事件
        sc = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.v("test","onNext...");
                    }
                });

    }


    @Override
    protected void onStop() {
        super.onStop();
        /*if(sc!=null) {
            Log.v("test","uns...");
            sc.unsubscribe();
        }*/

    }
}