package com.example.scaletopview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.carter.BaseActivity;
import com.example.carter.R;

import org.w3c.dom.Text;

/**
 * Created by carter on 5/6/17.
 */

public class Test extends BaseActivity{

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, Test.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_test);
    }
}
