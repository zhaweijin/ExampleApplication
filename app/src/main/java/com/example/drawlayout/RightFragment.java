package com.example.drawlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carter.R;

/**
 * Created by zwj on 3/28/18.
 */

public class RightFragment extends Fragment implements View.OnClickListener{

    private DrawerLayout drawer_layout;
    private FragmentManager fManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_right, container, false);
        view.findViewById(R.id.btn_one).setOnClickListener(this);
        view.findViewById(R.id.btn_two).setOnClickListener(this);
        view.findViewById(R.id.btn_three).setOnClickListener(this);
        fManager = getActivity().getSupportFragmentManager();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                ContentFragment cFragment1 = new ContentFragment("1.点击了右侧菜单项一",android.R.color.holo_blue_light);
                fManager.beginTransaction().replace(R.id.fly_content,cFragment1).commit();
                drawer_layout.closeDrawer(Gravity.END);
                break;
            case R.id.btn_two:
                ContentFragment cFragment2 = new ContentFragment("2.点击了右侧菜单项二",android.R.color.holo_red_light);
                fManager.beginTransaction().replace(R.id.fly_content,cFragment2).commit();
                drawer_layout.closeDrawer(Gravity.END);
                break;
            case R.id.btn_three:
                ContentFragment cFragment3 = new ContentFragment("3.点击了右侧菜单项三",android.R.color.holo_green_light);
                fManager.beginTransaction().replace(R.id.fly_content,cFragment3).commit();
                drawer_layout.closeDrawer(Gravity.END);
                break;
        }
    }

    public void setDrawerLayout(DrawerLayout drawer_layout){
        this.drawer_layout = drawer_layout;
    }

}