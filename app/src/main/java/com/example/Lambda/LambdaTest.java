package com.example.Lambda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.example.Util.CarterLogger;
import com.example.carter.BaseActivity;
import com.example.carter.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by carter on 3/18/17.
 */
public class LambdaTest extends BaseActivity{

    final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    @BindView(R.id.button)
    Button button;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LambdaTest.class);
        activity.startActivity(intent);
    }

    public void threadTest(){
        CarterLogger.v(">>>>>>>>>>>>>>>>>>");
        r1.run();
        //r2.run();
        CarterLogger.v("####################");
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda_test);


        button.setOnClickListener(v -> CarterLogger.v("is onClick......"));

        //listOutTest();
    }


    Runnable r1 = () -> { System.out.println(this); };
    //Runnable r2 = () -> { System.out.println(toString()); };

    //public String toString() {  return "Hello, world"; }



    public void listOutTest(){
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("222");
        list.add("FDF");
        list.add("2ASS");
        list.add("Wxx");
        //直接输出
        Stream.of(list).forEach(name -> CarterLogger.v(name));
        //简单的转换输出
        Stream.of(list)
                .map(x -> x.toUpperCase())
                .forEach(y -> CarterLogger.v("Upper##A"+y));

        //Predicate<String> predicate =
        //过滤转换，并且输出打印
        List<String> tt = Stream.of(list)
                .filter(name -> name.startsWith("2"))
                .collect(Collectors.toList());
        Stream.of(tt).forEach( name -> CarterLogger.v("@@@"+name));

        //Rxjava 类似lambda输出格式
        Observable.from(Arrays.asList(1, 2, 3, 4, 5))
                .filter(integer -> integer % 2 == 0)
                .map(integer -> integer * integer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Toast.makeText(this, String.valueOf(integer), Toast.LENGTH_SHORT).show());




    }

}
