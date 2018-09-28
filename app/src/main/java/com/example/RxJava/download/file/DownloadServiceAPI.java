package com.example.RxJava.download.file;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by zwj on 6/4/18.
 */

public interface DownloadServiceAPI {

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}