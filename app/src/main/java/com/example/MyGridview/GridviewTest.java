package com.example.MyGridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ScrollView;

import com.example.ViewPaperTest.PageTest;
import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.example.view.ScaleGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by carter on 8/14/17.
 */

public class GridviewTest extends BaseActivity implements OnItemViewSelectedListener{

    @BindView(R.id.gridView)
    ScaleGridView gridview;

    @BindView(R.id.scroolview)
    ScrollView scroolview;

    ShoppingCartGridAdapter gridAdapter;
    List<String> datas;

    private boolean viewFinished = false;
    private int lastPosition;
    private int x;
    private int y;

    private int totalsize = 0;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, GridviewTest.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_test);



        datas = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            datas.add("name"+i);
        }
        totalsize = datas.size();
        gridAdapter = new ShoppingCartGridAdapter(this,datas);
        gridview.setAdapter(gridAdapter);



        gridview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                if (viewFinished) {
                    viewFinished=false;
                    Log.v("carter", "view finished...."+lastPosition);
                    gridview.setSelection(lastPosition);
                    //scroolview.scrollBy(x,y);
                }
            }
        });

        gridview.setSelection(11);




    }


    @Override
    public void OnItemViewSelectedListener(int position) {
        Log.v("carter","main......"+position);
        lastPosition = position;
        if(position>totalsize-8){
            ArrayList<String> data = new ArrayList<>();
            for (int i = 40; i < 70; i++) {
                data.add("name"+i);
            }


            gridAdapter.addData(data);
            totalsize = totalsize+data.size();

            Log.v("carter","y=="+scroolview.getScrollY());
            Log.v("carter","X=="+scroolview.getScaleX());
            x = scroolview.getScrollX();
            y = scroolview.getScrollY();
            gridview.setSelection(-1);
            viewFinished = true;

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_MENU){
            //scroolview.scrollBy(0,0);
            Log.v("carter","size="+gridAdapter.getCount());
        }
        return super.onKeyDown(keyCode, event);
    }
}
