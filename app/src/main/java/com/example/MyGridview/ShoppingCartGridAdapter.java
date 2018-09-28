package com.example.MyGridview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.carter.R;

import java.util.List;


public class ShoppingCartGridAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;
    private OnItemViewSelectedListener onItemSelectedListener;

    private String tag = "GridAdapter";

    public ShoppingCartGridAdapter(Context context, List<String> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.onItemSelectedListener = (OnItemViewSelectedListener) context;
        this.mDatas = mDatas;
    }


    /**
     * 分页添加的数据
     * @param entities
     */
    public void addData(List<String> entities) {
        if (null != entities) {
            this.mDatas.addAll(entities);
            notifyDataSetInvalidated();
            //notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            //convertView = mInflater.inflate(R.layout.activity_gridview_item_test, parent, false);
            convertView = new ItemView(mContext);
            ((ItemView)convertView).init(mContext,position);
            ((ItemView)convertView).setOnItemSelectedListener(onItemSelectedListener);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(mDatas.get(position));


        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(tag,"2222222222");
            }
        });*/


        return convertView;
    }


    final class ViewHolder {

        TextView name;

    }

}  