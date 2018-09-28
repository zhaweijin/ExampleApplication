package com.example.MyGridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;
import android.widget.ScrollView;

import com.example.carter.BaseActivity;
import com.example.carter.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by carter on 8/14/17.
 */

public class SimpleGridviewTest extends BaseActivity{


    @BindView(R.id.gridView)
    GridView gridView;

    List<String> datas = new ArrayList<>();
    SimpleGridAdapter simpleGridAdapter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SimpleGridviewTest.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_gridview_test);

        datas = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            datas.add("name"+i);
        }

        simpleGridAdapter = new SimpleGridAdapter(this,datas);
        gridView.setAdapter(simpleGridAdapter);

        gridView.setSelection(11);
    }
}
