package com.example.GalleryView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Gallery;

/**
 * Created by carter on 9/4/17.
 */

public class MyGallery extends Gallery{

    public MyGallery(Context context) {
        super(context);
    }

    public MyGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v("carter","onKeyDown");
        if(event.getAction()==KeyEvent.ACTION_DOWN){
            if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT && getSelectedItemId()==0){
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
