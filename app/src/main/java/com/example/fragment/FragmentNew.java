package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by carter on 6/20/17.
 */

public class FragmentNew extends Fragment{

    private int layoutID;

    public static FragmentNew getInstance(int layoutID){
        return new FragmentNew(layoutID);
    }

    public FragmentNew(){

    }

    public FragmentNew(int layoutID){
        this.layoutID = layoutID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(layoutID, null);
        return mRootView;
    }
}
