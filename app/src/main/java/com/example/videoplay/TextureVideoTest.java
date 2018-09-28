package com.example.videoplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.carter.R;

/**
 * Created by carter on 4/17/17.
 */

public class TextureVideoTest extends Activity{


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TextureVideoTest.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texture_video_play);

    }

}
