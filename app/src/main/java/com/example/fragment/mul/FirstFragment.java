package com.example.fragment.mul;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;

import com.example.carter.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class FirstFragment extends BaseMainFragment{

    @BindView(R.id.go)
    Button go;

    public static FirstFragment newInstance() {
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        RxView.clicks(go)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    Log.v("test","go");
                    start(FirstDetailFragment.newInstance());
                });
    }

    public static void onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Log.v("test", "OnKey事件==="+keyCode);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragmenta;
    }


}
