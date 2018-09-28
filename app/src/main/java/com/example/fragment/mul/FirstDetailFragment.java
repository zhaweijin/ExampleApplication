package com.example.fragment.mul;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.carter.R;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class FirstDetailFragment extends AbstractFragment {



    public static FirstDetailFragment newInstance() {
        Bundle args = new Bundle();
        FirstDetailFragment fragment = new FirstDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragmentb;
    }
}
