package com.example.DialogFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by zwj on 3/15/18.
 */

public class DialogTest extends BaseActivity{

    @BindView(R.id.show)
    Button show;

    ModeDialogFragement modeDialogFragement;


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DialogTest.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);


        RxView.clicks(show)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        if(modeDialogFragement==null){
                            modeDialogFragement = ModeDialogFragement.newInstance("1.0","new version");
                        }
                        modeDialogFragement.show(getFragmentManager(),"model");
                    }
                });
    }
}
