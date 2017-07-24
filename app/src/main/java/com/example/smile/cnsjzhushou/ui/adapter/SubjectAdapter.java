package com.example.smile.cnsjzhushou.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.Subject;
import com.example.smile.cnsjzhushou.common.Constant;


public class SubjectAdapter extends BaseQuickAdapter<Subject,BaseViewHolder> {

    public SubjectAdapter() {
        super(R.layout.template_imageview);
    }

    @Override
    protected void convert(BaseViewHolder helper, Subject item) {

        ImageView imageView =  helper.getView(R.id.imageview);
        String url = Constant.BASE_IMG_URL+item.getMticon();
        Glide.with(mContext).load(url).into(imageView);

    }
}
