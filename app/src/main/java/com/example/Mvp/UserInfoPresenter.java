package com.example.Mvp;

import android.os.Handler;

/**
 * Created by zwj on 5/31/18.
 */

public class UserInfoPresenter implements UserInfoContract.UserPresenter {
    private UserInfoContract.UserView view;


    public UserInfoPresenter(UserInfoContract.UserView view) {
        this.view = view;
    }

    @Override
    public void loadUserInfo() {
        String userId = view.loadUserId();
        view.showLoading();//接口请求前显示loading
        //这里模拟接口请求回调-
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //模拟接口返回的json，并转换为javaBean
                UserInfoModel userInfoModel = new UserInfoModel("小宝", 1, "杭州");
                view.showUserInfo(userInfoModel);
                view.dismissLoading();
            }
        }, 3000);
    }


}