package com.example.RecycleView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.carter.R;

import java.util.ArrayList;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HeaderAndFooterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HeaderAndFooterAdapter(ArrayList<String> entits) {
        super(R.layout.item_card, entits);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.info_text,item);
    }


}
