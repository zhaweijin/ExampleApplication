package com.example.Video;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.carter.BaseActivity;
import com.example.carter.R;

import butterknife.BindView;

/**
 * Created by carter on 7/21/17.
 */

public class VideoViewTest extends BaseActivity{

    @BindView(R.id.video_test1)
    RecommentVideoView video_test1;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoViewTest.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview_test);

        //http://220.112.196.79:81/live/92/live.m3u8
//http://4.bjclhx.cn/live/192925_7fa879b4e96a2e28f94c.flv?auth_key=1535390504-0-0-cc5e04d15b09d624c32115c6e8100641
        video_test1.setVideoURI(Uri.parse("http://4.bjclhx.cn/live/192925_7fa879b4e96a2e28f94c.flv?auth_key=1535390504-0-0-cc5e04d15b09d624c32115c6e8100641"));
        video_test1.start();




    }
}
