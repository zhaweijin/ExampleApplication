package com.example.Mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.carter.BaseActivity;

/**
 * Created by zwj on 5/31/18.
 */

public class MvpTest extends BaseActivity implements UserInfoContract.UserView{

    private UserInfoPresenter userPresenter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MvpTest.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPresenter = new UserInfoPresenter(this);


        userPresenter.loadUserInfo();
    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"showLoading",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        Toast.makeText(this,"dismissLoading",Toast.LENGTH_SHORT).show();
    }

    @Override
    public String loadUserId() {
        return null;
    }

    @Override
    public void showUserInfo(UserInfoModel userInfoModel) {
        Toast.makeText(this,"showUserInfo="+userInfoModel.getName(),Toast.LENGTH_SHORT).show();
    }
}
