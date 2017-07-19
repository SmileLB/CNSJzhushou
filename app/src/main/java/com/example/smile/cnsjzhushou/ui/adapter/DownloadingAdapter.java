package com.example.smile.cnsjzhushou.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.common.Constant;
import com.example.smile.cnsjzhushou.common.imageloader.ImageLoader;
import com.example.smile.cnsjzhushou.ui.widget.DownloadButtonConntroller;
import com.example.smile.cnsjzhushou.ui.widget.DownloadProgressButton;

import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;


public class DownloadingAdapter extends BaseQuickAdapter<DownloadRecord,BaseViewHolder> {

    private DownloadButtonConntroller mDownloadButtonConntroller;

    public DownloadingAdapter(RxDownload rxDownload) {
        super(R.layout.template_app_downloading);
        mDownloadButtonConntroller = new DownloadButtonConntroller(rxDownload);

        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadRecord item) {

        AppInfo appInfo = mDownloadButtonConntroller.downloadRecord2AppInfo(item);

        ImageLoader.load(Constant.BASE_IMG_URL+appInfo.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name,appInfo.getDisplayName());

        helper.addOnClickListener(R.id.btn_download);
        View viewBtn  = helper.getView(R.id.btn_download);

        if (viewBtn instanceof DownloadProgressButton){
            DownloadProgressButton btn = (DownloadProgressButton) viewBtn;
            mDownloadButtonConntroller.handClick(btn,item);
        }
    }
}
