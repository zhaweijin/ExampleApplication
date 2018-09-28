package com.example.Retrofit;


import android.app.Activity;

import com.example.Util.CarterLogger;
import com.example.Util.ComUtils;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by carter on 3/3/17.
 */
public class RetrofitTest {

    /**
     * rxjava+retrofit+okhttp 使用
     */


    private RequestServer mRequestServer;


    String baseUrl = "http://139.196.92.240/wapcenter/requesthome/";

    public RetrofitTest() {

    }

    public void test() {
        initRequestServer();
    }


    public void rxjavaRetrofitTest(Activity mActivity) {

        OkHttpClient okHttpClient = ComUtils.getOkHttpClient(mActivity);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //这个方法必须有,这样才能返回含有数据的实体类
                .addConverterFactory(GsonConverterFactory.create())
                //要使用retroift和rexjava配合使用这个方法必须有,不然会报Unable to create call adapter
                // for rx.Observable<com.ethanco.retrofit2_0test.HomeTopBean>异常
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)//整合ok
                .build();

        NetService apiService = retrofit.create(NetService.class);

        Observable<RetrofitBean> observable = apiService.load("false");
        observable.subscribeOn(Schedulers.io())//使请求网络的操作运行在子线程
                .observeOn(AndroidSchedulers.mainThread())//使后面的subscribe运行在主线程
                .subscribe(new Subscriber<RetrofitBean>() {
                    @Override
                    public void onCompleted() { //请求网络完成后回调,成功就只回调一次,失败不回调
                        CarterLogger.v(">>>>>>completed");
                    }

                    @Override
                    public void onError(Throwable e) {//失败时回调
                        CarterLogger.v(">>>>>error");   //url 错误或者是没有网络
                    }

                    @Override
                    public void onNext(RetrofitBean bean) {//成功时回调,这就是我们想要的;
                        CarterLogger.v("........"+bean.getResultCode()+",errorMsg="+bean.getErrorMsg());
                    }
                });
    }


    public interface RequestServer {
        @POST("mobileLogin/submit.html")
        Call<String> getString(@Query("loginname") String loginname,
                               @Query("nloginpwd") String nloginpwd);
    }

    public void initRequestServer() {
        CarterLogger.d("init");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://106.3.227.33/pulamsi/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        mRequestServer = retrofit.create(RequestServer.class);
    }


    public void login() {
        Call<String> call = mRequestServer.getString("userName", "1234");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                CarterLogger.d("sucess=="+response.toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                CarterLogger.d("error");
            }
        });
    }


    public interface NetService {

        @GET("weather")
        Call<RetrofitBean> loadeather(@Query("cityname") String cityname, @Query("key") String apiKey);


        @GET("weather")
        Call<RetrofitBean> load(@Query("cityname") String cityname, @Query("key") String apiKey);


        // http://139.196.92.240/wapcenter/requesthome/homepage?hasAllFloor=false
        @GET("homepage")
        Observable<RetrofitBean> load(@Query("hasAllFloor") String b);

        //@Path注解　是需要引用到URL路径当中的
        //@Field 需要配合@Post  @FormUrlEncoded 一起使用，不然报错
        //@Query get post 都能够使用
        //@Header 校验
        @GET("/api/v1/device/setname/{deviceID}/{deviceName}")
        void setDeviceName(@Header("Authorization") String token,
                           @Path("deviceID") String deviceID,
                           @Path("deviceName") String deviceName);

        /**
         * retrofit 支持 rxjava 整合
         * 这种方法适用于新接口
         */
        @GET("weather")
        Observable<RetrofitBean> getWeatherData(@Query("cityname") String cityname, @Query("key") String apiKey);


        @POST("user/哈哈")
        Observable<RetrofitBean> post(@Field("name") String name, @Field("pwd") String pwd);
    }
}




