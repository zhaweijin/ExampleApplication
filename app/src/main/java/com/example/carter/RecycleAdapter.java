package com.example.carter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by carter on 9/22/17.
 */

public class RecycleAdapter extends RecyclerView.Adapter {
    private ArrayList<String> items = new ArrayList<>();

    //header布局标志
    private static final int TYPE_HEADER=0;
    //普通布局标志
    public static final int TYPE_NORMAL=1;

    //普通布局标志
    public static final int TYPE_FOOT=2;

    //headerview的布局
    private View mHeaderView;

    private View mFootView;

    private int oldtotalSize;

    public View getmHeaderView() {
        return mHeaderView;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    public void setmFootView(View mFootView){
        this.mFootView = mFootView;
    }

    public RecycleAdapter(ArrayList<String> items) {
        this.items = items;
    }


    @Override
    public int getItemViewType(int position) {
        if(mHeaderView==null) return TYPE_NORMAL;
        if(position==0)return TYPE_HEADER;//将header插入到顶部
        if(position==getItemCount()-1)return TYPE_FOOT;//将header插入到顶部
        return TYPE_NORMAL;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new HeadViewHolder(mHeaderView);

        if (mFootView != null && viewType == TYPE_FOOT)
            return new HeadViewHolder(mFootView);

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, viewGroup, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position)==TYPE_HEADER) {
            Log.v("11","11");
            return;
        }


        if(getItemViewType(position)==TYPE_FOOT) {
            Log.v("11","22");
            return;
        }

        RecycleAdapter.ViewHolder viewHolder = (RecycleAdapter.ViewHolder) holder;

        String info = items.get(position-1);
        View view = viewHolder.itemView;

        TextView textView = (TextView) view.findViewById(R.id.info_text);
        textView.setText(info);


    }

    public void addData(ArrayList<String> entities) {
        if (null != entities) {
            oldtotalSize = items.size();
            this.items.addAll(entities);
            notifyItemRangeChanged(oldtotalSize, entities.size());
        }
    }

    /*public void upateData(ArrayList<String> entities){
        int oldsize=items.size();
        items.clear();
        notifyItemRangeRemoved(0,oldsize);
        items.addAll(entities);
        notifyItemRangeChanged(0,entities.size());
    }*/


    @Override
    public int getItemCount() {
        return items.size() +2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /*private class HeadViewHolder extends RecyclerView.ViewHolder {


        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }*/

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    public boolean isHeader(int position) {
        return position == 0;
    }


    public boolean isFoot(int position) {
        return position == getItemCount()-1;
    }


}