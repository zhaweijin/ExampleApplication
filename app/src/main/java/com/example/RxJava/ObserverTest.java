package com.example.RxJava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.Util.CarterLogger;
import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by carter on 2/23/17.
 */
public class ObserverTest extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.text)
    TextView textView;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ObserverTest.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxobserver_test_main);

//        dataChange();
        button_test(button);
//        listview_test();

/*        try {
            TestBean testBean = new TestBean();
            java.lang.reflect.Field field= testBean.getClass().getField("name");
            int k = (int)field.get("");
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }


    /**
     * Scheduler线程切换
     *这种场景经常会在“后台线程取数据，主线程展示”的模式中看见
     */
    public void test1(){
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                       CarterLogger.v("number=="+integer);
                    }
                });
    }


    //使用timer做定时操作。当有“x秒后执行y操作”类似的需求的时候，想到使用timer
    public void time_test() {

        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        CarterLogger.d("completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        CarterLogger.e("error");
                    }

                    @Override
                    public void onNext(Long number) {
                        CarterLogger.d("hello world");
                    }
                });
    }

    /**
     * 使用interval做周期性操作。  前面是间隔时间
     */
    public void interval_test() {
        Observable.interval(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        CarterLogger.d("completed");

                    }

                    @Override
                    public void onError(Throwable e) {
                        CarterLogger.e("error");
                    }

                    @Override
                    public void onNext(Long number) {
                        CarterLogger.d("hello world");
                    }
                });
    }

    /**
     *使用throttleFirst防止按钮重复点击
     * debounce也能达到同样的效果
     */
    public void button_test(View view){
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        CarterLogger.v("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {
                         CarterLogger.v("button subscrber onNext");
                        textView.setText(">>>>>>>>>>");
                    }
                });

    }



/*    private void listview_test(){
        ListView listView = new ListView(this);
        RxAdapterView.itemClicks(listView)
                .throttleFirst(1,TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return null;
                    }
                });

    }*/

    Observer observer;
    Subscriber subscribe;
    private void testSchPer(){
        final int INITIAL_DELAY = 1;
        final int POLLING_INTERVAL = 5;
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> observer) {

                Schedulers.newThread().createWorker()
                        .schedulePeriodically(new Action0() {
                            @Override
                            public void call() {
                                //observer.onNext(doNetworkCallAndGetStringResult());
                            }
                        }, INITIAL_DELAY, POLLING_INTERVAL, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

                CarterLogger.d("polling….");
            }
        });
    }


    private void dataChange(){

        Observable.just("1", "2", "2", "3", "4", "5")
                .map(name -> Integer.parseInt(name))
                .filter(x -> x > 1)
                .distinct()
                //.take(3)
                .reduce((integer, integer2) -> integer.intValue() + integer2.intValue())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        CarterLogger.v(">>>"+integer);
                    }
                });
    }


    private void create_observable_test(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                 if(subscriber.isUnsubscribed()){
                     subscriber.onNext("hello");
                     subscriber.onCompleted();
                 }
            }
        });

        observable.subscribe(showSub);
    }

    Subscriber showSub = new Subscriber() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {

        }
    };


    /**
     * flatMap链式网络请求，多次请求网络，主要是onErrorReturn处理异常问题
     */
    /*private void test(){

        String input="";
        try{
            org.json.JSONObject json = new org.json.JSONObject();
            json.put("mac", DeviceInfo.getInstance(mContext).getMac());
            json.put("sn",DeviceInfo.getInstance(mContext).getSn());
            input = json.toString();
            //input = input.replace("{","%7B").replace("}","%7D");
            Utils.print(tag,"test1input="+input);
        }catch (Exception e){
            e.printStackTrace();
        }

        Subscription s = RetrofitClient.getCommodityAPI()
                .httpGetToken(input)
                .onErrorReturn(new Func1<Throwable, TokenData>() {
                    @Override
                    public TokenData call(Throwable throwable) {
                        Utils.print(tag,"has error="+throwable.getMessage());
                        return null;
                    }
                })
                .flatMap(new Func1<TokenData, Observable<CollectionData>>() {

                    @Override
                    public Observable<CollectionData> call(TokenData tokenData) {
                        Utils.print(tag,"222wsaasd");
                        if(tokenData==null)
                            return null;
                        Utils.print(tag,"gettoken return value="+tokenData.getReturnValue()+",message="+tokenData.getErrorMessage());
                        String input="";
                        try{
                            org.json.JSONObject json = new org.json.JSONObject();
                            json.put("userid", ConStant.getInstance(mContext).userID);
                            json.put("pageIndex",pageIndex);
                            json.put("pageSize",ConStant.PAGESIZE);
                            input = json.toString();
                            input = input.replace("{","%7B").replace("}","%7D");
                            Utils.print(tag,"test2input="+input);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return RetrofitClient.getCommodityAPI().httpGetAllFavoriteData(input, ConStant.getInstance(mContext).Token);
                    }
                })
                .onErrorReturn(new Func1<Throwable, CollectionData>() {
                    @Override
                    public CollectionData call(Throwable throwable) {
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CollectionData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CollectionData collectionData) {

                    }
                });

    }*/



    /*//http://60.206.137.159:8082/api/open/tv/auction/cancelAuctionOrder?params=%7B"orderSn":"491770794486022144"%7D&sign=3E70CA0E3D13BE76714996E1BDD2B7E8
    @POST("tv/auction/cancelAuctionOrder?")
    Call<Object> httpCancelAcutionOrder(@Query(value = "params", encoded = true) String params, @Query("sign") String sign);*/

    private void testOutput(){
        /*Call<Object> call = RetrofitClient.getCommodityAPI()
                .httpCancelAcutionOrder(input,sign);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.e(tag, "response:" + response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Utils.print(tag,"error");
            }
        });*/
    }
}
