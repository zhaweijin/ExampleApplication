package com.example.Mvp;

/**
 * @carter
 */
public interface UserInfoContract {

    interface UserView {

        void showLoading();//展示加载框

        void dismissLoading();//取消加载框展示

        void showUserInfo(UserInfoModel userInfoModel);//将网络请求得到的用户信息回调

        String loadUserId();//假设接口请求需要一个userId
    }

    interface UserPresenter {
        void loadUserInfo();
    }

}
