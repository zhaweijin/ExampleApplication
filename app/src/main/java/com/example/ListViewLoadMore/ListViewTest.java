package com.example.ListViewLoadMore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.jakewharton.rxbinding.widget.AdapterViewItemSelectionEvent;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ListViewTest extends ListActivity implements OnScrollListener {
    private ListView listView;
    private int visibleLastIndex = 0; //最后的可视项索引
    private int visibleItemCount;  // 当前窗口可见项总数
    private ListViewAdapter adapter;
    private View loadMoreView;
    private Button loadMoreButton;
    private Handler handler = new Handler();

    private boolean loading = false;

    private int count = 30;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ListViewTest.class);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_loadmore_test);

        loadMoreView = getLayoutInflater().inflate(R.layout.listview_load_more_foot, null);
        loadMoreButton = (Button) loadMoreView.findViewById(R.id.loadMoreButton);

        listView = getListView();    //获取id是list的ListView

        //listView.addFooterView(loadMoreView); //设置列表底部视图

        initAdapter();

        setListAdapter(adapter);    //自动为id是list的ListView设置适配器

        listView.setOnScrollListener(this);  //添加滑动监听


        listView.setOnItemSelectedListener(onItemSelectedListener);
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            items.add(String.valueOf(i + 1));
        }
        adapter = new ListViewAdapter(this, items);
    }

    /**
     * 滑动时被调用
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    /**
     * 滑动状态改变时被调用
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = adapter.getCount() - 1; //数据集最后一项的索引
        int lastIndex = itemsLastIndex + 1;    //加上底部的loadMoreView项
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
            //如果是自动加载,可以在这里放置异步加载数据的代码
            if(!loading) {
                loadMore();
                Log.i("test", "loading...");
            }
        }
    }

    /**
     * 点击按钮事件
     * @param view
     */
    public void loadMore() {
        loading = true;
        listView.addFooterView(loadMoreView);
        loadMoreButton.setText("loading..."); //设置按钮文字loading
        adapter.notifyDataSetChanged();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                loadData();
                listView.removeFooterView(loadMoreView);
                adapter.notifyDataSetChanged(); //数据集变化后,通知adapter
                //listView.setSelection(visibleLastIndex - visibleItemCount + 1); //设置选中项
                //listView.setSelection();
                loadMoreButton.setText("load more"); //恢复按钮文字
                loading = false;

            }
        }, 3000);
    }

    /**
     * 模拟加载数据
     */
    private void loadData() {
        int count = adapter.getCount();
        for (int i = count; i < count + 10; i++) {
            adapter.addItem(String.valueOf(i + 1));
        }
    }



    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.v("test","onItemSelected");
            if((position==adapter.getCount()-1) && (adapter.getCount()<count)){
                Log.v("test","load more");
                if(!loading) {
                    loadMore();
                    Log.i("test", "loading...");
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
