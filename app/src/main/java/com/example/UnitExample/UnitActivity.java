package com.example.UnitExample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.jakewharton.rxbinding.view.RxView;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zwj on 3/8/18.
 */

public class UnitActivity extends BaseActivity implements SurfaceHolder.Callback,Camera.PreviewCallback{


    private String TAG ="UnitActivity";

    @BindView(R.id.playsound)
    Button playsound;

    @BindView(R.id.sv)
    SurfaceView surfaceView;

    private Camera mCamera;

    SoundPool soundPool;

    private static final int REQUEST_CODE_PERMISSION = 2;
    // Storage Permissions
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, UnitActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;

        if (currentapiVersion >= Build.VERSION_CODES.M) {
            verifyPermissions(this);
        }

        //需要提前加载，因为有些设备，还没有来的及加载完成，就开始播放，肯定就不会有声音
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        soundPool.load(this, R.raw.face_unlock_sound, 1); // 加载资源，返回1

        unitTestPlaySound();

        //startCamera();


    }


    private void unitTestPlaySound(){
        RxView.clicks(playsound)
                .throttleFirst(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {
                        Log.v("play","next");
                        playSound();
                    }
                });
    }




    private void playSound() {

        soundPool.play(1, 1, 1, 0, 0, 1); // 播放，返回0
    }

    private void startCamera(){

        //SurfaceView中的getHolder方法可以获取到一个SurfaceHolder实例
        SurfaceHolder holder = surfaceView.getHolder();
        //为了实现照片预览功能，需要将SurfaceHolder的类型设置为PUSH
        //这样，画图缓存就由Camera类来管理，画图缓存是独立于Surface的
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.v(TAG,"surfaceCreated");
        mCamera = onCreatCamera();//
        if (mCamera == null) {
            return;
        }
        //int angleCamera = 90;
        //mCamera.setDisplayOrientation(angleCamera);
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mCamera.setPreviewCallback(this); //!!!!必须匹配释放mCamera.setPreviewCallback(null);
        Log.v(TAG,"surfaceCreated2");

        //mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        //考虑拉伸变形问题
        //当SurfaceView尺寸变化时（包括设备横屏竖屏改变时时），需要重新设定相关参数
        if (holder.getSurface() == null) {
            //检查SurfaceView是否存在
            return;
        }

        //改变设置前先关闭相机
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用最佳比例配置重启相机
        try {
            mCamera.setPreviewDisplay(holder);
            final Camera.Parameters parameters = mCamera.getParameters();
            final Camera.Size size = getBestPreviewSize(width, height);
            parameters.setPreviewSize(size.width, size.height);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.v(TAG,"surfaceDestroyed");
        if(mCamera!=null){
            //mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera=null;
        }
    }


    private Camera.Size getBestPreviewSize(int width, int height) {
        Camera.Size result = null;
        final Camera.Parameters p = mCamera.getParameters();
        //特别注意此处需要规定rate的比是大的比小的，不然有可能出现rate = height/width，但是后面遍历的时候，current_rate = width/height,所以我们限定都为大的比小的。
        float rate = (float) Math.max(width, height)/ (float)Math.min(width, height);
        float tmp_diff;
        float min_diff = -1f;
        for (Camera.Size size : p.getSupportedPreviewSizes()) {
            float current_rate = (float) Math.max(size.width, size.height)/ (float)Math.min(size.width, size.height);
            tmp_diff = Math.abs(current_rate-rate);
            if( min_diff < 0){
                min_diff = tmp_diff ;
                result = size;
            }
            if( tmp_diff < min_diff ){
                min_diff = tmp_diff ;
                result = size;
            }
        }
        return result;
    }

    private Camera onCreatCamera() {
        Camera camera = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int cameraCount = Camera.getNumberOfCameras();
        int facing = Camera.CameraInfo.CAMERA_FACING_FRONT;

        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == facing || cameraCount == 1) {
                try {
                    Log.v(TAG,"Idx="+camIdx);
                    camera = Camera.open(camIdx);
                } catch (RuntimeException e) {
                    Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
                }
                break;
            }
        }
        Log.d(TAG, "onCreatCamera");
        return camera;
    }

    private static boolean verifyPermissions(Activity activity) {
        // Check if we have write permission
        int write_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read_persmission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int camera_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (write_permission != PackageManager.PERMISSION_GRANTED ||
                read_persmission != PackageManager.PERMISSION_GRANTED ||
                camera_permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_REQ,
                    REQUEST_CODE_PERMISSION
            );
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }
}
