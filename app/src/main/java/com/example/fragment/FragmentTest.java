package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Transition;
import android.util.Log;
import android.view.KeyEvent;

import com.example.carter.BaseActivity;
import com.example.carter.R;


/**
 * 验证Fragement后退机制
 * Created by carter on 6/20/17.
 */

public class FragmentTest extends BaseActivity{

    private String tag = "FragementTest";


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, FragmentTest.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);

        Log.v(tag,"onCreate");
        /**
         * FragmentTransaction 必须每次初始化
         */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentNew A = FragmentNew.getInstance(R.layout.fragmenta);
        transaction.replace(R.id.container,A);
        transaction.addToBackStack("aa");
        transaction.commit();


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Log.v(tag,">>>"+getSupportFragmentManager().getBackStackEntryCount());
            if(getSupportFragmentManager().getBackStackEntryCount()==1){
                clearBackStack();
                finish();
            }
        }else if(keyCode==KeyEvent.KEYCODE_MENU){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            FragmentNew B = FragmentNew.getInstance(R.layout.fragmentb);
            transaction.replace(R.id.container,B);
            transaction.addToBackStack("bb");
            transaction.commit();
        }
        return super.onKeyDown(keyCode, event);
    }


    private void clearBackStack() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStackImmediate();
        }
    }
}
