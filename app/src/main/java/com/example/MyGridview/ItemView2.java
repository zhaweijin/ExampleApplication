package com.example.MyGridview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.carter.R;


/**
 * Created by carter on 8/14/17.
 */

public class ItemView2 extends LinearLayout {

    private Context context;
    private Animation scaleSmallAnimation;
    private Animation scaleBigAnimation;


    public ItemView2(Context context) {
        super(context);
        init(context);
    }

    public ItemView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {

        this.context = context;
        //Utils.print(tag, "init size=" + itemTotalCount+",pagePosition="+pagePosition);


        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.activity_gridview_item_test, null);
        layout.setOnFocusChangeListener(onFocusChangeListener);
        addView(layout);


    }


    OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            Log.v("carter","hasFocus="+hasFocus);
            if (hasFocus) {
                zoomOut();
            } else {
                zoomIn();
            }
        }
    };

    private void zoomIn() {
        if (scaleSmallAnimation == null) {
            scaleSmallAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale_small);
        }
        startAnimation(scaleSmallAnimation);
    }

    private void zoomOut() {
        if (scaleBigAnimation == null) {
            scaleBigAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale_big);
        }
        startAnimation(scaleBigAnimation);
    }



}
