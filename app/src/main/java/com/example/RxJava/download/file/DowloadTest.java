package com.example.RxJava.download.file;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.carter.BaseActivity;
import com.example.carter.R;

/**
 * Created by zwj on 6/4/18.
 */

public class DowloadTest extends BaseActivity{


    public static final String MESSAGE_PROGRESS = "message_progress";

    private Button btn_download;
    //private ProgressBar progress;
    //private TextView progress_text;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DowloadTest.class);
        activity.startActivity(intent);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(MESSAGE_PROGRESS)) {
                Download download = intent.getParcelableExtra("download");
                Log.v("test","download......"+download.getProgress());
                /*progress.setProgress(download.getProgress());
                if (download.getProgress() == 100) {
                    progress_text.setText("File Download Complete");
                } else {
                    progress_text.setText(StringUtils.getDataSize(download.getCurrentFileSize())
                            +"/"+
                            StringUtils.getDataSize(download.getTotalFileSize()));
                }*/
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_file_test);
        btn_download = (Button) findViewById(R.id.download);
        //progress = (ProgressBar) findViewById(R.id.progress);
        //progress_text = (TextView) findViewById(R.id.progress_text);

        verifyStoragePermissions(this);

        registerReceiver();

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DowloadTest.this, DownloadService.class);
                startService(intent);
            }
        });
    }

    private void registerReceiver() {

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
