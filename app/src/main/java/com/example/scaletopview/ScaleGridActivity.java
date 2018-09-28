package com.example.scaletopview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.hiveview.error.code.ErrorReport;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by carter on 5/5/17.
 */

public class ScaleGridActivity extends BaseActivity{

    @BindView(R.id.gridView)
    MyGridView gridView;

    GridViewAdapter gridViewAdapter;

    private View oldView;


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ScaleGridActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_top_test);



        new ErrorReport().showDialog(this, new ErrorReport.ErrorDialogListener() {
            @Override
            public void onDismiss() {
                finish();
            }
        });

        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<8;i++){
            list.add("name"+i);
        }

        gridViewAdapter = new GridViewAdapter(this,list);
        gridView.setAdapter(gridViewAdapter);


        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("aaa","onItemSelected");
                if(oldView!=null){
                    oldView.animate().scaleX(1.0f).scaleY(1.0f).start();
                }
                view.animate().scaleX(1.2f).scaleY(1.2f).start();
                oldView = view;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.v("aaa","call");
                        gridView.setSelection(0);
                    }
                });

    }


}
