package com.example.fragment.mul;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;

import com.example.carter.R;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by zwj on 5/31/18.
 */

public class OneMulFragmentAcitvity extends SupportActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, OneMulFragmentAcitvity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechat_activity_main);

        if (findFragment(FirstFragment.class) == null) {
            loadRootFragment(R.id.fl_container, FirstFragment.newInstance());
        }
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Log.d("ActionBar", "OnKey事件");
        if(getTopFragment() instanceof FirstFragment){
            FirstFragment.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
