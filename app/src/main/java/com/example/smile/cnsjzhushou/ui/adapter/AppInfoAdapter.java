package com.example.smile.cnsjzhushou.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.common.imageloader.ImageLoader;

/**
 * Created by LiBing
 * on 2017/7/12 0012
 * describe:
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder> {

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    public AppInfoAdapter() {
        super(R.layout.template_appinfo);
    }


    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageLoader.load(baseImgUrl+item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name,item.getDisplayName())
                .setText(R.id.txt_brief,item.getBriefShow());



    }
}
