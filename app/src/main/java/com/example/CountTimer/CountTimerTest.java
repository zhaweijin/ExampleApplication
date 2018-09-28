package com.example.CountTimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.carter.BaseActivity;
import com.example.carter.R;


/**
 * Created by carter on 11/15/17.
 */

public class CountTimerTest extends BaseActivity{

    private TextView textView;
    private CommodityCountDownTimer timer;
    private final long TIME = 60*26*60 * 1000L;
    private final long INTERVAL = 1000L;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, CountTimerTest.class);
        activity.startActivity(intent);
    }

    public class CommodityCountDownTimer extends CountDownTimer {
        public CommodityCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long time = millisUntilFinished / 1000;
            Log.v("carter","time="+time);

            long day = 0;
            long hour=0;
            long minute=0;
            long second=0;

            if(time>3600*24){
                day = time/(3600*24);
                hour = (time-day*24*3600) / 3600;
                minute = (time-day*24*3600- hour * 3600) / 60;
                second = time-day*24*3600 - hour * 3600 - minute * 60;
                textView.setText(String.format("倒计时开始 %02d天 %02d:%02d:%02d", day,hour,minute,second));
            }else{
                hour = time / 3600;
                minute = (time- hour * 3600) / 60;
                second = time - hour * 3600 - minute * 60;
                textView.setText(String.format("倒计时开始  %02d:%02d:%02d", hour,minute,second));
            }


        }

        @Override
        public void onFinish() {
            textView.setText("倒计时结束  00:00:00");
            cancelTimer();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_timer_test);
        textView = (TextView) findViewById(R.id.tv);
        startTimer();
    }

    public void start(View view) {
        startTimer();
    }

    public void cancel(View view) {
        textView.setText("倒计时结束  00:00");
        cancelTimer();
    }

    /**
     * 开始倒计时
     */
    private void startTimer() {
        if (timer == null) {
            timer = new CommodityCountDownTimer(TIME, INTERVAL);
        }
        timer.start();
    }

    /**
     * 取消倒计时
     */
    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }
}
