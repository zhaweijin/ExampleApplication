package com.example.MyGridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carter.R;

import java.util.List;


public class SimpleGridAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;


    private String tag = "GridAdapter";

    public SimpleGridAdapter(Context context, List<String> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
    }


    /**
     * 分页添加的数据
     * @param entities
     */
    public void addData(List<String> entities) {
        if (null != entities) {
            this.mDatas.addAll(entities);
            notifyDataSetChanged();
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
            convertView = mInflater.inflate(R.layout.simple_gridview_item_test, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(mDatas.get(position));




        return convertView;
    }


    final class ViewHolder {

        TextView name;

    }

}  