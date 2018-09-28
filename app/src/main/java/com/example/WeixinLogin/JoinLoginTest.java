package com.example.WeixinLogin;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.carter.BaseActivity;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by zwj on 4/12/18.
 */

public class JoinLoginTest extends BaseActivity{


    private static final String APP_ID = "wxe480d9d9635697bb";
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void regToWx(){
        iwxapi = WXAPIFactory.createWXAPI(this,APP_ID,true);
        iwxapi.registerApp(APP_ID);
    }
}
