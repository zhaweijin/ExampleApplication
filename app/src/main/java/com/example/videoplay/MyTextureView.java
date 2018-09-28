package com.example.videoplay;

/**
 * Created by carter on 4/17/17.
 */


import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;


/**
 * Created by Xingliu on 2016/12/5.
 */
public class MyTextureView extends TextureView implements TextureView.SurfaceTextureListener {
    private  String FILE_NAME = "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4";

    private MediaPlayer mMediaPlayer;
    private Context mContext;


    public MyTextureView(Context context) {
        super(context);
        mContext = context;
        initView();

    }

    public MyTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public void stopPlay() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

    }

    private void initView() {
        setSurfaceTextureListener(this);

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {

        Surface surface = new Surface(surfaceTexture);
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(FILE_NAME);
            mMediaPlayer.setSurface(surface);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
