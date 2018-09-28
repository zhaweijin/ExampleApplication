package com.example.MyGridview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.carter.R;


public class FocusRelativeLayout extends RelativeLayout {

    private Animation scaleSmallAnimation;
    private Animation scaleBigAnimation;

    private int position;
    private OnItemViewSelectedListener onItemSelectedListener;


    private final static String TAG = "carter";

    public FocusRelativeLayout(Context context) {
        super(context);
    }

    public FocusRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(TAG, "onFocusChanged: ");
        if (gainFocus) {
            Log.v(TAG,"pos>>>"+position);
            onItemSelectedListener.OnItemViewSelectedListener(position);
            setTextLayoutSelected();
            zoomOut();
        } else {
            setTextLayoutUnSelect();
            zoomIn();
        }
    }

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


    public void setPostion(int postion){
        this.position = postion;
    }



    public void setTextLayoutSelected(){
        findViewById(R.id.layout_textview).setBackgroundResource(android.R.color.holo_red_light);
        findViewById(R.id.name).setAlpha(1f);
        findViewById(R.id.price).setAlpha(1f);
        findViewById(R.id.icon).setAlpha(1f);
    }

    public void setTextLayoutUnSelect(){
        findViewById(R.id.layout_textview).setBackgroundResource(android.R.color.darker_gray);
        findViewById(R.id.name).setAlpha(0.5f);
        findViewById(R.id.price).setAlpha(0.5f);
        findViewById(R.id.icon).setAlpha(0.2f);
    }


    public void setOnItemSelectedListener(OnItemViewSelectedListener itemSelectedListener){
        this.onItemSelectedListener = itemSelectedListener;
    }

}
