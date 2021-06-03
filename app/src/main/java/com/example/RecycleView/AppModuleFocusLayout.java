package com.example.RecycleView;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.carter.R;


public class AppModuleFocusLayout extends RelativeLayout {

    private Animation scaleSmallAnimation;
    private Animation scaleBigAnimation;

    private int position;


    private boolean isValid = true;


    /**
     * 区分模块
     */
    private int mType = -1;


    /**
     * 是否编辑模式
     */
    private boolean isEditMode = false;

    private final static String tag = "FocusRelativeLayout";

    public AppModuleFocusLayout(Context context) {
        super(context);

    }

    public AppModuleFocusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public AppModuleFocusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        try {
            if (gainFocus) {
                zoomOut();
            } else {
                zoomIn();
            }
        } catch (Exception e) {
            e.printStackTrace();
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


    /**
     * 设置模块调用的item,分类或收藏或
     *
     * @param type
     */
    public void setType(int type) {
        this.mType = type;
    }

}
