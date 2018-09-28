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


import com.example.carter.R;


/**
 * Created by carter on 8/14/17.
 */

public class ItemView extends LinearLayout {

    private Context context;
    private int position;
    private Animation scaleSmallAnimation;
    private Animation scaleBigAnimation;
    private OnItemViewSelectedListener onItemSelectedListener;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Context context,int position) {

        this.context = context;
        this.position = position;
        //Utils.print(tag, "init size=" + itemTotalCount+",pagePosition="+pagePosition);


        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.activity_gridview_item_test, null);
        layout.setOnFocusChangeListener(onFocusChangeListener);
        addView(layout);


    }


    View.OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            Log.v("carter","hasFocus="+hasFocus);
            if (hasFocus) {
                onItemSelectedListener.OnItemViewSelectedListener(position);
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


    public void setOnItemSelectedListener(OnItemViewSelectedListener onItemSelectedListener){
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
