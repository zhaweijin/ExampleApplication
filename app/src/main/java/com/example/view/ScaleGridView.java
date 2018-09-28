package com.example.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;

/**
 * 主要用于网格购物车展示
 * Created by carter on 5/5/17.
 */

public class ScaleGridView extends GridView {

    private int position = 0;

    public ScaleGridView(Context context, AttributeSet attrs) {//构造函数
        super(context, attrs);
        setChildrenDrawingOrderEnabled(true);
    }


    @Override
    protected int getChildDrawingOrder(int childCount, int i) {

        position = getSelectedItemPosition() - getFirstVisiblePosition();
        Log.v("test", "p===" + position + ",i=" + i);
        if (position < 0) {
            return i;
        } else {
            if (i == childCount - 1) {//这是最后一个需要刷新的item
                if (position > i) {
                    position = i;
                }
                return position;
            }
            if (i == position) {//这是原本要在最后一个刷新的item
                return childCount - 1;
            }
        }
        return i;
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }



}