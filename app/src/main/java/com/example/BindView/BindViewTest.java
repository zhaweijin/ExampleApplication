package com.example.BindView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carter.BaseActivity;
import com.example.carter.R;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by carter on 3/1/17.
 */
public class BindViewTest extends BaseActivity{


    @BindView(R.id.message)
    TextView message;

    @BindString(R.string.app_name)
    String app;

    @BindColor(R.color.colorPrimary)
    int red_color;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bindview_main);


        message.setText(">>>>>>>>>>>test..."+app);
        message.setTextColor(red_color);



    }


    @OnClick(R.id.button)   //给 button1 设置一个点击事件
    public void showToast(){
        Toast.makeText(this, "is a click", Toast.LENGTH_SHORT).show();
    }

    /*@OnClick(R.id.bb)
    public void button2_onClick(){
        Toast.makeText(this, "is a click button2", Toast.LENGTH_SHORT).show();
    }*/


    /*private boolean click = false;
    @OnClick(R.id.bb)
    public void button2_onclick(Button button){
        if(click){
            click = false;
            button.setText("1111");
        }else{
            click = true;
            button.setText("2222");
        }
    }*/



}
