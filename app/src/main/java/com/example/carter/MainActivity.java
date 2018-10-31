package com.example.carter;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.BindView.BindViewTest;
import com.example.BugReportTest.BugTest;
import com.example.profilerMemoryTest.RxLifecycleComponentsActivity;
import com.example.view.FileTitleTextView;
import com.example.view.MarqueeText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;


public class MainActivity extends BaseActivity {


    final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
    private final String tag = MainActivity.class.getSimpleName();


    @BindView(R.id.text2)
    FileTitleTextView text2;

    @BindView(R.id.user_center)
    Button user_center;

    @BindView(R.id.collection)
    Button collection;

    @BindView(R.id.special)
    Button special;

    @BindView(R.id.info)
    Button info;

    @BindView(R.id.order)
    Button order;

    @BindView(R.id.shop)
    Button shop;

    @BindView(R.id.marquee_text)
    MarqueeText marquee_text;

    @BindView(R.id.tv_test_width)
    TextView tv_test_width;

    RelativeLayout.LayoutParams layoutParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("test","sssssssssssss");
        //switchInputMethod(this,"com.baidu.input_baidutv/com.baidu.input_baidutv.ImeService");

        //ScaleGridActivity.launch(this);
        //finish();

        //S905EthernetManager s905EthernetManager = new S905EthernetManager(this);
        //Log.v("test","pp==="+s905EthernetManager.getEthernetIpAddress());


        //s905EthernetManager.setEthernetIpConfiguration(true,"","","","");


        /*RetrofitTest retrofitTest = new RetrofitTest();
        retrofitTest.rxjavaRetrofitTest(this);*/

        /*try {
            Cursor cursor = null;
            ContentResolver resolver = getContentResolver();
            cursor = resolver.query(Uri.parse("content://HiveViewAuthoritiesDataProvider/TABLE_WHITE_LIST"), null, null, null, null);
            int count = cursor.getCount();
            Log.v("carter", "hiveview. updateBgRunWhiteList. got new bgrun List:...................22222 " + cursor.getCount());
            if (count > 0) {
                cursor.moveToFirst();
                do {
                    Log.v("carter", ">>" + cursor.getString(cursor.getColumnIndex("bundleId")));
                } while (cursor.moveToNext());
            }
        } catch (Throwable t) {

        }*/



        //DialogTest.launch(this);
//        DrawLayoutTest.launch(this);
//        GlideTest.launch(this);
//        DITest.launch(this);
//        MvpTest.launch(this);
//        OneMulFragmentAcitvity.launch(this);
//        DowloadTest.launch(this);
       // ViewPagerActivity.launch(this);

        //SqlTestActivity.launch(this);
        /*Intent intent = new Intent(this, TestIntentService.class);
        startService(intent);*/

        //UnitActivity.launch(this);

        //Logger.i("activity created"); // 打印info
        //Logger.d("test....dd");

        //RecycleViewTest.launch(this);

        //VideoViewTest.launch(this);
        //Test.launch(this);
        //finish();

        //CountTimerTest.launch(this);
        //finish();

        RxLifecycleComponentsActivity.launch(this);

//        FilpperTest.launch(this);


        //UnitActivity.launch(this);
        //GalleryTest.launch(this);
        //finish();

        //test 专题
        /*Intent intent = new Intent("com.hiveview.dianshang.action.COMMODITYSPECIAL");
        intent.putExtra("specSn","431654500697927680");
        startActivity(intent);
        finish();*/

        /*Intent intent = new Intent("com.hiveview.dianshang.action.SHOPPINGCART");
        startActivity(intent);
        finish();*/




        order.setOnClickListener(onClickListener);
        user_center.setOnClickListener(onClickListener);
        special.setOnClickListener(onClickListener);
        collection.setOnClickListener(onClickListener);
        info.setOnClickListener(onClickListener);
        shop.setOnClickListener(onClickListener);


        //Log.v("cc",">>>>"+StringFilter("22322O32399999"));


        //Log.v("carter",formatInvalidString("pan@!***"));

        //MyUtils.showToast(this,"carter show");


//        startBindviewTest();

       /* View view = new View(this);
        view.setOnClickListener(v -> CarterLogger.v("is onClick"));

*/

//        LambdaTest.launch(this);
//          ListViewTest.launch(this);
        //PageTest.launch(this);

        //DatabaseActivityTest.launch(this);


        //TextureVideoTest.launch(this);


        //testPremission();


        //FragmentTest.launch(this);

        //JsonStringTest.launch(this);


        /*text.setOnFocusChangeListener(onFocusChangeListener);
        text2.setOnFocusChangeListener(onFocusChangeListener);*/
    }

    public boolean StringFilter(String str) throws PatternSyntaxException {
        String regEx = "^[a-zABCDEFGHJKLMNPQRTUWXY0-9]+$";

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    public static String formatInvalidString(String str) {
        /*String str=s.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& ;*（）——+|{}【】‘；：”“’。、？|-]", "");
        str = str.replace("\\s","");
        str = str.replace("\n","");
        return str;*/

        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {

            }
        }
    };


    private String getValidString(String str) {
        String re = "";
        int length = 0;
        int pos = 0;
        for (int i = 0; i < str.length(); i++) {
            String bb = str.substring(i, i + 1);
            // 生成一个Pattern,同时编译一个正则表达式,其中的u4E00("一"的unicode编码)-\u9FA5("龥"的unicode编码)
            boolean cc = java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", bb);
            if (cc == false) {
                Log.v(tag, "en"); //字母
                length = length + 1;
            } else {
                Log.v(tag, "zh");//中文
                length = length + 2;
            }
            if (length > 14) {
                pos = i;
                break;
            }
        }
        Log.v("cc", "pos=" + pos);
        re = str.substring(0, pos);
        return re;
    }


    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.activity_pop_window_test, null);
        // 设置按钮的点击事件

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);


        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                android.R.color.black));

        popupWindow.setFocusable(false);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);


    }

    private void testPremission() {
        File file = new File("/system/etc/remote.conf");
        BufferedReader reader = null;
        String[] tmpString = new String[1];
        String res = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            int line = 0;
            while (line < tmpString.length && (tmpString[line] = reader.readLine()) != null) {
                System.out.println("line " + line + ": " + tmpString[line]);
                line++;
            }
            res = reader.readLine();
            Log.e("aa", "read message:" + res);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void startRxBusTest() {
        startActivity(new Intent(this, RxBusTest.class));
    }

    private void startBindviewTest() {
        startActivity(new Intent(this, BindViewTest.class));
    }


    private void bugTest() {
        BugTest bugTest = new BugTest();
//        bugTest.bugReport();
        bugTest.bugReport2();
    }


    private boolean start = true;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v("carter", "keycode==" + keyCode);
        if (keyCode == KeyEvent.KEYCODE_MENU) {

        } else if (keyCode == KeyEvent.KEYCODE_0) {
            Log.v("carter", "1111");
            AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        }
        return super.onKeyDown(keyCode, event);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.collection:
                    /*intent = new Intent("com.hiveview.dianshang.action.HOME");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("page", 2);
                    startActivity(intent);*/

                    break;
                case R.id.order:
                    intent = new Intent("com.hiveview.dianshang.action.HOME");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("page", 1);
                    startActivity(intent);
                    break;
                case R.id.user_center:
                    intent = new Intent("com.hiveview.dianshang.action.HOME");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("page", 3);
                    startActivity(intent);
                    break;
                case R.id.special:
                    intent = new Intent("com.hiveview.dianshang.action.COMMODITYSPECIAL");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("specSn", "424732544429199360");
                    startActivity(intent);
                    break;
                case R.id.info:
                    intent = new Intent("com.hiveview.dianshang.action.COMMODITYINFO");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("sn", "401823897671962624");
                    startActivity(intent);
                    break;
                case R.id.shop:
                    intent = new Intent("com.hiveview.dianshang.action.SHOPPINGCART");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
            }
        }
    };



}
