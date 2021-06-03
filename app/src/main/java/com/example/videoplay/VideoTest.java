package com.example.videoplay;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.example.view.LoadVideoView;

import butterknife.BindView;

/**
 * Created by carter on 10/9/17.
 */

public class VideoTest extends BaseActivity{

    @BindView(R.id.video_view)
    LoadVideoView video_view;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoTest.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_test);

        video_view.setVideoURI(Uri.parse("http://testdcpimg.cloudp.cc/m3u8/shangguan01/20200427/1254705178217553922.mp4"));
        video_view.start();
    }
}
