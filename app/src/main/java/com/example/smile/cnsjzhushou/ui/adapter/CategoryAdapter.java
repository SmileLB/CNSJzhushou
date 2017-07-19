package com.example.smile.cnsjzhushou.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.Category;
import com.example.smile.cnsjzhushou.common.Constant;
import com.example.smile.cnsjzhushou.common.imageloader.ImageLoader;

/**
 * Created by LiBing
 * on 2017/7/15 0015
 * describe:
 */

public class CategoryAdapter extends BaseQuickAdapter<Category,BaseViewHolder> {


    public CategoryAdapter() {
        super(R.layout.template_category);
    }
    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        helper.setText(R.id.text_name,item.getName());
        ImageLoader.load(Constant.BASE_IMG_URL+item.getIcon(), (ImageView) helper.getView(R.id.img_icon));
    }
}
