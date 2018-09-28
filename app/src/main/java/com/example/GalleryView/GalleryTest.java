package com.example.GalleryView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.example.view.CircleImageView;
import com.example.view.RoundImageView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by carter on 8/23/17.
 */

public class GalleryTest extends BaseActivity{


    @BindView(R.id.gallery1)
    MyGallery gallery1;

    @BindView(R.id.gallery2)
    Gallery gallery2;

    @BindView(R.id.focus)
    ImageView focus;

    @BindView(R.id.circleview)
    CircleImageView circleview;

    @BindView(R.id.cc)
    RoundImageView cc;

    @BindView(R.id.dd)
    SimpleDraweeView dd;

    private String[] myImageIds = {"0","1","2"};

    private Text2Adapter text2Adapter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, GalleryTest.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_test);

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        text2Adapter = new Text2Adapter(list,this);
        gallery1.setAdapter(text2Adapter);


        gallery1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("carter","pos=="+position);
            }
        });

        gallery1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.v("carter","onkey");
                return false;
            }
        });

        gallery1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.v("carter","onFocusChange=="+hasFocus);
            }
        });



        gallery1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("carter","onItemSelected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayList<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("4");
        list2.add("5");
        gallery2.setAdapter(new Text2Adapter(list2,this));
        gallery2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.v("carter","gallery2 hasfocus=="+hasFocus);
            }
        });
        gallery2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("carter","gallery2 onItemSelected==");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF0000"));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        //circleview.setCircleBitmap(bitmap);
        circleview.setImageDrawable(colorDrawable);



        //circleview.setImageDrawable(getResources().getDrawable(R.drawable.test_cloud));

        //circleview.setImageResource(getResources().getColor(android.R.color.white));

        //circleview.setFillColor(getResources().getColor(android.R.color.holo_red_dark));


        //cc.setCircleBitmap(bitmap);

        //dd.setImageDrawable(getResources().getDrawable(R.drawable.b));
    }



    public class TextAdapter extends BaseAdapter {
        private Context mContext;

        public TextAdapter(Context c) {
            mContext = c;
        }

        public int getCount() /* 涓�瀹氳閲嶅啓鐨勬柟娉昰etCount,浼犲洖鍥剧墖鏁扮洰鎬绘暟 */ {
            return myImageIds.length;
        }

        public Object getItem(int position) /* 涓�瀹氳閲嶅啓鐨勬柟娉昰etItem,浼犲洖position */ {
            return position;
        }

        public long getItemId(int position) /* 涓�瀹氳閲嶅啓鐨勬柟娉昰etItemId,浼犲洖position */ {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView i = new TextView(mContext);
            i.setTextSize(60);
            i.setTextColor(android.graphics.Color.WHITE);
            i.setText(myImageIds[position % myImageIds.length]);
            i.setLayoutParams(new Gallery.LayoutParams(112, 70));
            i.setGravity(Gravity.CENTER);
            i.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    // TODO Auto-generated method stub
                    Log.v("carter","focus=="+hasFocus);
                    /*if (hasFocus) {
                        focus.setVisibility(View.VISIBLE);
                    } else {
                        focus.setVisibility(View.INVISIBLE);
                    }*/
                }
            });
            return i;
        }
    }



    public class Text2Adapter extends BaseAdapter {
        private List<String> lists;
        private LayoutInflater mInflater;
        private int itemSelect = -1;

        public void setItemSelect(int selectID){
            this.itemSelect = selectID;
            notifyDataSetChanged();
        }


        public class ViewHolder {
            TextView name;

            ImageView focus;

            RelativeLayout commodity_item_information;
        }

        public Text2Adapter(List<String> lists,Context context) {
            this.lists = lists;
            mInflater = LayoutInflater.from(context);
        }

        public int getCount()  {
            return lists.size();
        }

        public Object getItem(int position)  {
            return position;
        }

        public long getItemId(int position)  {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.select_commodity_item_textview, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.item_type_textview);
                holder.focus = (ImageView)convertView.findViewById(R.id.focus);
                holder.commodity_item_information = (RelativeLayout)convertView.findViewById(R.id.commodity_item_information);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.name.setText(lists.get(position));
            if(itemSelect==position){
                holder.focus.setVisibility(View.VISIBLE);
            }else{
                holder.focus.setVisibility(View.INVISIBLE);
            }


            holder.commodity_item_information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("carter","onclick...");
                }
            });

            return convertView;
        }
    }
}
