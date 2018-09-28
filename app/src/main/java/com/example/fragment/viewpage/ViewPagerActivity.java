package com.example.fragment.viewpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.carter.BaseActivity;
import com.example.carter.R;

import butterknife.BindView;

/**
 * Created by zwj on 6/6/18.
 */

public class ViewPagerActivity extends BaseActivity{

    @BindView(R.id.pager)
    ViewPager viewPager;

    private View head;



    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ViewPagerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_fragment_test);

        head = LayoutInflater.from(this).inflate(R.layout.test_fragment_head,null);


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
    }


    private class PagerAdapter extends FragmentPagerAdapter {
        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.v("test","pos=="+position);
            switch (position) {
                case 0:
                    return OneFragment.getInstance(head);
                case 1:
                    return SecondFragment.getInstance(head);
                case 2:
                    return ThridFragment.getInstance(head);
            }
            return OneFragment.getInstance(head);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return getString(this.tabTitles[position]);
            return "";
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}
