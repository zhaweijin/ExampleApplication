package com.example.RxJava.download.file;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by zwj on 6/4/18.
 */

public class DownloadAPI {
    private static final String TAG = "DownloadAPI";
    private static final int DEFAULT_TIMEOUT = 15;
    public Retrofit retrofit;


    public DownloadAPI(String url, DownloadProgressListener listener) {

        DownloadProgressInterceptor interceptor = new DownloadProgressInterceptor(listener);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public void downloadAPK(@NonNull String url, final File file, Subscriber subscriber) {
        Log.d(TAG, "downloadAPK: " + url);
        retrofit.create(DownloadServiceAPI.class)
                .download(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(responseBody -> {
                    try {
                        FileUtils.writeFile(responseBody.byteStream(), file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return "";
                })
                .subscribe(subscriber);
    }


}
