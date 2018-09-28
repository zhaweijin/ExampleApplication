package com.example.ViewPaperTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Lambda.LambdaTest;
import com.example.carter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carter on 4/5/17.
 */

public class PageTest extends Activity{

    ViewPager pager;
    List<View> views;


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, PageTest.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_test);

        /*pager = (ViewPager)findViewById(R.id.view_pager);
        pager.setRotation(-90f);
        views = new ArrayList<View>();

        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.view1, null);
        View view2 = mLi.inflate(R.layout.view2, null);
        View view3 = mLi.inflate(R.layout.view3, null);
        views.add(view3);
        views.add(view2);
        views.add(view1);

        MyPagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);*/



        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.view_pager2);

        views = new ArrayList<View>();

        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.view1, null);
        View view2 = mLi.inflate(R.layout.view2, null);
        View view3 = mLi.inflate(R.layout.view3, null);
        views.add(view3);
        views.add(view2);
        views.add(view1);

        VerticalAdapter adapter = new VerticalAdapter(views);
        verticalViewPager.setAdapter(adapter);
    }


    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;    //这行代码很重要，它用于判断你当前要显示的页面
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(views.get(position));
            return views.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }


    }



}

