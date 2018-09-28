package com.example.DialogFragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.carter.R;
import com.orhanobut.logger.Logger;

/**
 * Created by zwj on 3/15/18.
 */

public class ModeDialogFragement extends DialogFragment{


    private TextView text;

    private String mVersionCode;
    private String mDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.activity_dialog_fragement_test, container);

        text = (TextView)view.findViewById(R.id.text);
        text.setText("ccarterss");

        return view;
    }


    public static ModeDialogFragement newInstance(String versionCode, String description) {
        ModeDialogFragement fragment = new ModeDialogFragement();
        Bundle bundle = new Bundle();
        bundle.putString("version", versionCode);
        bundle.putString("description", description);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mVersionCode = bundle.getString("version");
        mDescription = bundle.getString("description");

        Logger.d(mVersionCode+"###"+mDescription);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            //dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);

            dialog.getWindow().setLayout(600,400);
        }
    }
}
