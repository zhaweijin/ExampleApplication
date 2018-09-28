package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by carter on 7/19/17.
 */
public class LoadVideoView extends VideoView {

    public Context mContext;

    public LoadVideoView(Context context) {
        super(context);
        this.mContext = context;
    }

    public LoadVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public LoadVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }



}