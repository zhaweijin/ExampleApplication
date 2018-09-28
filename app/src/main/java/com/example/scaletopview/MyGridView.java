package com.example.scaletopview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;

/**
 * Created by carter on 5/5/17.
 */

public class MyGridView extends GridView {

    private int position = 0;

    public MyGridView(Context context, AttributeSet attrs) {//构造函数
        super(context, attrs);
        setChildrenDrawingOrderEnabled(true);
    }

    /*@Override
    protected int getChildDrawingOrder(int childCount, int i) {
        Log.v("test","cout=="+childCount+"i=="+i);
        return childCount - i - 1;//倒序
    }*/


    @Override

    protected int getChildDrawingOrder(int childCount, int i) {

        position = getSelectedItemPosition() - getFirstVisiblePosition();
        Log.v("test","p==="+position+",i="+i);
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

}