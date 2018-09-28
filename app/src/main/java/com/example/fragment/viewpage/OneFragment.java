package com.example.fragment.viewpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.carter.R;

/**
 * Created by zwj on 6/6/18.
 */

public class OneFragment extends android.support.v4.app.Fragment{


    private View head;

    public static OneFragment getInstance(View view) {
        OneFragment fragment = new OneFragment();
        fragment.setHeadView(view);
        return fragment;
    }

    public void setHeadView(View view){
        this.head = view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_one_fragment, container,false);

        LinearLayout contain = (LinearLayout)view.findViewById(R.id.top_head);

        contain.addView(head);
        ((Button)head.findViewById(R.id.one)).setText("one");
        return view;
    }
}
