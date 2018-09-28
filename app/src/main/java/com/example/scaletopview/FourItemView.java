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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;


@SuppressLint("NewApi")
public class FourItemView extends FrameLayout implements OnFocusChangeListener {


    private static final String TAG = "test";
    private Context context;

    private Button[] buttons = new Button[12];


    private int xcount = 0;
    private int ycount = 0;

    private int selectPos = 0;


    public FourItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public FourItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FourItemView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        //启用子视图排序功能
        setChildrenDrawingOrderEnabled(true);

        xcount = 4;
        ycount = buttons.length/xcount;
        FrameLayout.LayoutParams layoutParams;// = new LinearLayout.LayoutParams(100,40);

        int p=0;
        for(int i=0;i<ycount;i++){  //y轴
            for(int j=0;j<xcount;j++){ //x轴
                Log.v(TAG,"p=="+p);
                buttons[p] = new Button(context);
                buttons[p].setText("button"+p);
                buttons[p].setTag("item"+p);
                buttons[p].setBackgroundResource(android.R.color.holo_red_dark);
                buttons[p].setOnFocusChangeListener(this);
                layoutParams = new FrameLayout.LayoutParams(100,40);
                int left = j*100;
                int top = i*40;
                Log.v(TAG,"left=="+left+",top="+top);
                layoutParams.setMargins(left,top,0,0);
                addView(buttons[p],layoutParams);
                p++;
            }
        }

    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int position = selectPos;
        //Log.v(TAG,"selectPos==="+selectPos);
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
        //Log.v(TAG,"i===="+i+",count="+childCount);
        return i;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            handViewPos(v);
            invalidate();
            v.animate().scaleX(1.9f).scaleY(1.9f).start();
        } else {
            v.animate().scaleX(1f).scaleY(1f).start();
        }
    }


    private void handViewPos(View view){
        if(view.getTag()!=null){
            String tag = view.getTag().toString();

            selectPos = Integer.parseInt(tag.substring(tag.indexOf("item")+4,view.getTag().toString().length()));

            Log.v(TAG,"selectpos>>>>"+selectPos%buttons.length);
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
