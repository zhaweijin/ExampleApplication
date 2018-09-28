package com.example.VIewFlilpper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.Lambda.LambdaTest;
import com.example.carter.BaseActivity;
import com.example.carter.R;

import butterknife.BindView;

/**
 * Created by zwj on 2/28/18.
 */

public class FilpperTest extends BaseActivity{

    @BindView(R.id.vp)
    ViewFlipper vp;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, FilpperTest.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filpper_test);

        fillView();
    }



    private void fillView() {
        for (int i = 0; i < 2; i++) {
            TextView view = new TextView(this);
            view.setTextColor(Color.BLACK);
            ViewFlipper.LayoutParams params = new ViewFlipper.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            view.setLayoutParams(params);
            view.setText("色带士大夫谁" + i);

            vp.addView(view);
        }



        vp.setFlipInterval(1000);


        //自动循环滚动
        /*vp.setAutoStart(true);
        vp.setFlipInterval(2000);
        vp.startFlipping();*/

        //出入动画
        vp.setOutAnimation(this, R.anim.push_up_out);
        vp.setInAnimation(this, R.anim.push_down_in);

        vp.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                vp.stopFlipping();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_MENU){
            Log.v("t","menu...");
            vp.showNext();
        }
        return super.onKeyDown(keyCode, event);
    }
}
