/**
 * @Title PageItemMaker.java
 * @Package com.hiveview.cloudscreen.video.view
 * @author haozening
 * @date 2015-5-5 上午9:45:02
 * @Description
 * @version V1.0
 */
package com.example.scaletopview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;


@SuppressLint("NewApi")
public class ItemView extends LinearLayout {


    private static final String TAG = "test1";
    private Context context;

    private FourItemView item1;
    private FourItemView item2;
    private FourItemView item3;

    private int selectPos = 0;


    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItemView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        //启用子视图排序功能
        setChildrenDrawingOrderEnabled(true);


        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,40);
        setOrientation(LinearLayout.VERTICAL);

        item1 = new FourItemView(context);
        addView(item1,layoutParams);

        item2 = new FourItemView(context);
        addView(item2,layoutParams);

        item3 = new FourItemView(context);
        addView(item3,layoutParams);


    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int position = selectPos;
        /*Log.v(TAG,"selectPos==="+selectPos);
        if (position < 0) {
            return i;
        } else {
            if (i == childCount - 1) {
                if (position > i) {
                    position = i;
                }
                return position;
            }
            if (i == position) {
                return childCount - 1;
            }
        }
        Log.v(TAG,"i===="+i+",count="+childCount);
        return i;*/
        Log.v(TAG,"selectPos==="+selectPos);
        Log.v(TAG,"i===="+i);
        int a = 0;
        if(position==1) {
            a = 2;
        }else{
            a = i;
        }
        Log.v(TAG,"a===="+a);
        return a;
    }





    private void handViewPos(View view){
        if(item1.indexOfChild(view)>=0){
            selectPos = 0;
        }else if(item2.indexOfChild(view)>=0){
            selectPos = 1;
        }else if(item3.indexOfChild(view)>=0){
            selectPos = 2;
        }

    }

    public void ScaleOutAnimation(View view) {
        ScaleAnimation myAnimation_Scale = new ScaleAnimation(1.0f, 1.9f, 1.0f, 1.9f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        myAnimation_Scale.setInterpolator(new AccelerateInterpolator());
        AnimationSet aa = new AnimationSet(true);
        aa.addAnimation(myAnimation_Scale);
        aa.setDuration(500);
        aa.setFillAfter(true);

        view.startAnimation(aa);
    }


}
