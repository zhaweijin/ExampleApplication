package com.example.carter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;


/**
 * Created by carter on 8/15/17.
 */

public class Test extends FragmentActivity{


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, Test.class);
        activity.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sample);


        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        System.out.println("message===w=="+width+",height="+height);


    }


}
