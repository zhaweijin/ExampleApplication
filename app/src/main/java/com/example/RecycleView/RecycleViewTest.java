package com.example.RecycleView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.Util.Constant;
import com.example.carter.BaseActivity;
import com.example.carter.R;
import com.example.carter.RecycleAdapter;
import com.example.carter.ScrollGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

import static com.example.carter.R.id.recyclerView;
import static com.example.carter.R.layout.head;

/**
 * Created by carter on 3/23/17.
 */
public class RecycleViewTest extends BaseActivity{

    @BindView(recyclerView)
    RecyclerView mRecyclerView;

    /*@BindView(R.id.top)
    TextView top;*/
    private HeaderAndFooterAdapter headerAndFooterAdapter;

    private ItemAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private ArrayList<String> items = new ArrayList<>();


    private boolean initRequest = false;
    private int curPos=1;
    private View headerView;

    RecycleAdapter adapter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RecycleViewTest.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_test);


        Log.v("tt",">>>"+ Build.VERSION.SDK_INT);

        //test();

//        fastAdapter();
        //initViewCard();
        initListView();

        //initViewStaggerGrid();
    }


    /**
     * 模拟listview
     */
    private void initListView(){
        mRecyclerView = (RecyclerView) findViewById(recyclerView);
        mRecyclerView.setHasFixedSize(true);

        /**
         * 增加分割线
         */
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.HORIZONTAL, 2,ContextCompat.getColor(this, android.R.color.darker_gray)));
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayout.HORIZONTAL,false);//listview水平方向

        /**
         * 设置纵向滚动，也可以设置横向滚动
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //listview 垂直方向
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //构建一个临时数据源
        for (int i = 0; i < 220; i++) {
            items.add("item" + i);
            Log.v("carter","--"+items.get(i));
        }


        MyAdapter adapter = new MyAdapter(items);
        mRecyclerView.setAdapter(adapter);

    }




    /**
     * 网格滚动,模拟gridview
     */
    private void initViewCard() {
        //mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //列数为两列
        int spanCount = 4;

        /**
         * 横线网络滚动，配合adapter item android:layout_width="250dp" android:layout_height="match_parent"
         */
        //mLayoutManager = new GridLayoutManager(this,spanCount,LinearLayoutManager.HORIZONTAL,false);

        /**
         * 纵向网格滚动，配合adapter item android:layout_width="match_parent" android:layout_height="250dp"
         * 类型传统grid滚动风格
         */
        //mLayoutManager = new GridLayoutManager(this,spanCount);

        ScrollGridLayoutManager manager = new ScrollGridLayoutManager(this,spanCount);
        //manager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(manager);

        //构建一个临时数据源
        for (int i = 0; i < 220; i++) {
            items.add("item" + i);
        }

        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.BOTH_SET,10, Color.WHITE));



        mAdapter = new ItemAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
        initRequest = true;

        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(initRequest){
                    initRequest = false;
                    mRecyclerView.getChildAt(0).requestFocus();
                }
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.v("test","dx=="+dx+",dy=="+dy);

                Log.v("test","y==="+mRecyclerView.getScrollY());
                /*if(dy>0){
                    Log.v("test","isVis="+top.getVisibility());
                    top.setVisibility(View.GONE);
                }else{
                    //top.setVisibility(View.VISIBLE);
                }*/
            }
        });





    }


    /**
     * 瀑布式风格样式，随机修改高宽展示
     */
    /*private void initViewStaggerGrid() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //列数为两列
        int spanCount = 4;



        mLayoutManager = new StaggeredGridLayoutManager(spanCount,LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);
        //构建一个临时数据源
        for (int i = 0; i < 100; i++) {
            items.add("item" + i);
        }

        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));


        MyStaggeredGridAdapter myStaggeredGridAdapter = new MyStaggeredGridAdapter(items);
        mRecyclerView.setAdapter(myStaggeredGridAdapter);


    }*/



    public class MyAdapter extends RecyclerView.Adapter{

        private ArrayList<String> list = new ArrayList<>();
        public MyAdapter(ArrayList<String> list){
            this.list = list;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);


            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder = (MyViewHolder)holder;
            Log.v("carter","===="+list.get(position));
            viewHolder.textView.setText(list.get(position));


        }

        @Override
        public int getItemCount() {
            return list.size();
        }


    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        private TextView textView;
        private RelativeLayout item;

        private int position;

        private OnRecyclerViewListener onRecyclerViewListener;


        public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
            this.onRecyclerViewListener = onRecyclerViewListener;
        }

        public MyViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            textView = (TextView)mView.findViewById(R.id.info_text);
            item  =(RelativeLayout) mView.findViewById(R.id.item);
            mView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Toast.makeText(RecycleViewTest.this, "2222", Toast.LENGTH_SHORT).show();
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }
    }


    public interface OnRecyclerViewListener {
        void onItemClick(int position);
    }

    public static class ItemAdapter extends RecyclerView.Adapter {
        private ArrayList<String> items = new ArrayList<>();


        private RecyclerView recyclerView;
        private GridLayoutManager manager;

        public void setGridLayoutManager(GridLayoutManager mm){
            manager = mm;
        }

        public void setRecyclerView(RecyclerView reView){
            this.recyclerView = reView;
        }

        public ItemAdapter(ArrayList<String> items) {
            this.items = items;
        }

        @Override
        public ViewHolderA onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card,
                    viewGroup, false);
            return new ViewHolderA(view);
        }



        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolderA viewHolder = (ViewHolderA) holder;

            String info = items.get(position);
            View view = viewHolder.itemView;
            TextView textView = (TextView) view.findViewById(R.id.info_text);
            textView.setText(info);
            //手动更改高度，不同位置的高度有所不同
            //textView.setHeight(100 + (position % 3) * 30);
            viewHolder.card_view.setTag(""+position);

            viewHolder.card_view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    Log.v("tt","keycode=="+keyCode);
                    String tt = v.getTag().toString();
                    Log.v("tt","tag=="+tt);
                    if(event.getAction()==KeyEvent.ACTION_DOWN){
                        if(keyCode==KeyEvent.KEYCODE_DPAD_UP){
                            if(Integer.parseInt(tt)<3 && Integer.parseInt(tt)>=0){
                        if(recyclerView!=null){
                            //recyclerView.scrollToPosition(0);
                            manager.scrollToPositionWithOffset(0, 0);
                            //manager.setStackFromEnd(true);
                        }

                                //manager.scrollToPositionWithOffset(0, 0);
                                //manager.setStackFromEnd(true);
                            }
                        }
                    }

                    return false;
                }
            });

        }

        @Override
        public int getItemCount() {
            return items.size();
        }



        class ViewHolderA extends RecyclerView.ViewHolder {
            View mView;
            RelativeLayout card_view;

            public ViewHolderA(View itemView) {
                super(itemView);
                mView = itemView;
                card_view  =(RelativeLayout) mView.findViewById(R.id.card_view);
            }
        }
    }





    public class MyStaggeredGridAdapter extends RecyclerView.Adapter{

        private ArrayList<String> list = new ArrayList<>();
        private Random random;
        public MyStaggeredGridAdapter(ArrayList<String> list){
            random = new Random();
            this.list = list;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item2, parent, false);
            MyStaggeredGridViewHolder viewHolder = new MyStaggeredGridViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyStaggeredGridViewHolder viewHolder = (MyStaggeredGridViewHolder)holder;
            Log.v("carter","===="+list.get(position));
            viewHolder.textView.setText(list.get(position));

            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) viewHolder.layout_item.getLayoutParams();
            int height = random.nextInt(251-80)+80;
            layoutParams.height = height;
            viewHolder.layout_item.setLayoutParams(layoutParams);



        }

        @Override
        public int getItemCount() {
            return list.size();
        }


    }


    class MyStaggeredGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        private TextView textView;
        private RelativeLayout item;
        private LinearLayout layout_item;

        private int position;

        private OnRecyclerViewListener onRecyclerViewListener;


        public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
            this.onRecyclerViewListener = onRecyclerViewListener;
        }

        public MyStaggeredGridViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            textView = (TextView)mView.findViewById(R.id.info_text);
            item  =(RelativeLayout) mView.findViewById(R.id.item);
            layout_item = (LinearLayout)mView.findViewById(R.id.layout_item);
            mView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Toast.makeText(RecycleViewTest.this, "2222", Toast.LENGTH_SHORT).show();
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }
    }




    /**
     * 应用到adapter 内部，然后动态添加，删除数据，配合动画实现各种效果
     * @param position
     */
    public void addData(int position) {
        /*mDatas.add(position, "Insert One");
        notifyItemInserted(position);*/
    }

    public void removeData(int position) {
        /*mDatas.remove(position);
        notifyItemRemoved(position);*/
    }


    private void test() {

        int spanCount = 4;


        mLayoutManager = new GridLayoutManager(this,spanCount);
        for (int i = 0; i < 100; i++) {
            items.add("item" + i);
        }
        adapter = new RecycleAdapter(items);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(adapter.isHeader(position)){
                    return mLayoutManager.getSpanCount();
                }else {
                    return 1;
                }
            }
        });

        View headview = LayoutInflater.from(this).inflate(head, null);
        adapter.setmHeaderView(headview);
        initListener(headview);



        /*View footview = LayoutInflater.from(this).inflate(R.layout.foot, null);
        adapter.setmFootView(footview);*/


        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int postion = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
                if (adapter.isHeader(postion) || adapter.isFoot(postion)) {
                    outRect.set(0, 0, 0, 0);
                } else {
                    outRect.set(10, 10, 10, 10);
                }
            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);



        //mRecyclerView.smoothScrollToPosition(10);
        initRequest = true;
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(initRequest){
                    initRequest = false;
                    //mRecyclerView.getChildAt(10).requestFocus();
                }
            }
        });





    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_MENU){
            Log.v("test","key..............");

            //mAdapter.notifyDataSetChanged();
            //mAdapter.notifyItemRangeChanged(1,mAdapter.getItemCount());
        }
        return super.onKeyDown(keyCode, event);
    }


    private void setAdapter(ArrayList<String> items){
        adapter = new RecycleAdapter(items);

        //初始化头
        View headview = LayoutInflater.from(this).inflate(R.layout.head, null);
        adapter.setmHeaderView(headview);
        initListener(headview);

        mRecyclerView.setAdapter(adapter);
    }

    private void initListener(View head){
        Log.v("test","initListener");
        Button button1 = (Button)head.findViewById(R.id.button1);
        Button button2 = (Button)head.findViewById(R.id.button2);
        Button button3 = (Button)head.findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.clear();
                for (int i = 0; i < 50; i++) {
                    items.add("aaaitem" + i);
                }
                int oldpos=curPos;
                boolean isFromLeft=true;
                curPos=1;
                if(oldpos>curPos){
                    isFromLeft=true;
                }else{
                    isFromLeft=false;
                }
                setFastAdapter(items,isFromLeft);
                initRequest=true;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.clear();
                for (int i = 0; i < 50; i++) {
                    items.add("bbbitem" + i);
                }
                int oldpos=curPos;
                boolean isFromLeft=true;
                curPos=2;
                if(oldpos>curPos){
                    isFromLeft=true;
                }else{
                    isFromLeft=false;
                }
                setFastAdapter(items,isFromLeft);
                initRequest=true;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.clear();
                for (int i = 0; i < 50; i++) {
                    items.add("cccitem" + i);
                }
                int oldpos=curPos;
                boolean isFromLeft=true;
                curPos=3;
                if(oldpos>curPos){
                    isFromLeft=true;
                }else{
                    isFromLeft=false;
                }
                setFastAdapter(items,isFromLeft);

                initRequest=true;
            }
        });
    }



    private void fastAdapter(){
        int spanCount = 4;

        mLayoutManager = new GridLayoutManager(this,spanCount);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(adapter.isHeader(position) || adapter.isFoot(position)){
                    return mLayoutManager.getSpanCount();
                }else {
                    return 1;
                }
            }
        });
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int postion = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
                outRect.set(10, 10, 10, 10);
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);


        items.clear();
        for (int i = 0; i < 50; i++) {
            items.add("fast item" + i);
        }
        headerAndFooterAdapter = new HeaderAndFooterAdapter(items);
        headerAndFooterAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(headerAndFooterAdapter);

        headerView = LayoutInflater.from(this).inflate(head, null);
        headerAndFooterAdapter.addHeaderView(headerView);
        initListener(headerView);

        initRequest = true;
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(initRequest){
                    initRequest = false;
                    Log.v("test",">>>>request focus");
                    if(curPos==1){
                        ((Button)headerView.findViewById(R.id.button1)).requestFocus();
                    }else if(curPos==2){
                        ((Button)headerView.findViewById(R.id.button2)).requestFocus();
                    }else if(curPos==3){
                        ((Button)headerView.findViewById(R.id.button3)).requestFocus();
                    }
                }
            }
        });
    }


    private void setFastAdapter(ArrayList<String> items,boolean isFromLeft){
        headerAndFooterAdapter = new HeaderAndFooterAdapter(items);

        headerView = LayoutInflater.from(this).inflate(head, null);
        headerAndFooterAdapter.addHeaderView(headerView);
        initListener(headerView);

        if(isFromLeft){
            headerAndFooterAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        }else{
            headerAndFooterAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        }

        mRecyclerView.setAdapter(headerAndFooterAdapter);
    }
}
