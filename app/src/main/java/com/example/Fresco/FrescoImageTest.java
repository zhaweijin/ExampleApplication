package com.example.Fresco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.internal.util.RxRingBuffer;

public class FrescoImageTest extends BaseActivity implements View.OnTouchListener{

    @BindView(R.id.frsco_img1)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.frsco_img2)
    SimpleDraweeView mSimpleDraweeView2;
    @BindView(R.id.frsco_img3)
    SimpleDraweeView mSimpleDraweeView3;

    /**
     * 远程图片 	http://, https:// 	HttpURLConnection
     *本地文件 	file:// 	FileInputStream
     *Content provider 	content:// 	ContentResolver
     *asset目录下的资源 	asset:// 	AssetManager
     *res目录下的资源 	res:// 	Resources.openRawResource
     */



    private String imageUri1 = "http://img.ptcms.csdn.net/article/201503/30/5519091be9a85_middle.jpg?_=30474";
    private String imageUri2 = "http://ww1.sinaimg.cn/mw600/6345d84ejw1dvxp9dioykg.gif";
    private String imageUri3 = "http://p5.qhimg.com/t01d0e0384b952ed7e8.gif";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_image_test);





        Uri uri = Uri.parse(imageUri1);
        Uri uri2 = Uri.parse(imageUri2);
//        Uri uri3 = Uri.parse("res://" + getPackageName() + "/" + R.drawable.webp_5410);

        Uri uri3 = Uri.parse("asset://"+ getPackageName() + "/" +"webp_5410.webp");


        DraweeController  draweeController1 = Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true).build();
        mSimpleDraweeView.setController(draweeController1);
        mSimpleDraweeView.setOnTouchListener(this);

        DraweeController  draweeController2 = Fresco.newDraweeControllerBuilder().setUri(uri2).setAutoPlayAnimations(true).build();
//        mSimpleDraweeView2 = (SimpleDraweeView) findViewById(R.id.frsco_img2);
        mSimpleDraweeView2.setController(draweeController2);
        RoundingParams mRoundParams2 =  mSimpleDraweeView2.getHierarchy().getRoundingParams();
        mRoundParams2.setRoundAsCircle(true);
        mSimpleDraweeView2.getHierarchy().setRoundingParams(mRoundParams2);
        mSimpleDraweeView2.setOnTouchListener(this);

        DraweeController  draweeController3 = Fresco.newDraweeControllerBuilder().setUri(uri3).setAutoPlayAnimations(true).build();
        mSimpleDraweeView3.setController(draweeController3);
        mSimpleDraweeView3.setOnTouchListener(this);

//        loadResPic(this,mSimpleDraweeView3,R.drawable.webp_5410);

    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, FrescoImageTest.class);
        activity.startActivity(intent);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mSimpleDraweeView3.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                return  true;
            case MotionEvent.ACTION_UP:
                mSimpleDraweeView3.clearColorFilter();
                return  true;
        }
        return super.onTouchEvent(event);
    }


    public static void loadResPic(Context context, SimpleDraweeView simpleDraweeView, int id) {
        Uri uri = Uri.parse("res://" +
                context.getPackageName() +
                "/" + id);
        simpleDraweeView.setImageURI(uri);
    }
}
