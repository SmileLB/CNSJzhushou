package com.example.smile.cnsjzhushou.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.smile.cnsjzhushou.R;

public class SearchHistoryAdatper extends BaseQuickAdapter<String,BaseViewHolder> {
    public SearchHistoryAdatper() {
        super(R.layout.template_search_history);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.btn,item);
        helper.addOnClickListener(R.id.btn);
    }
}
