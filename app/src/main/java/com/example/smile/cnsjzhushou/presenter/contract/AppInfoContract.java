package com.example.smile.cnsjzhushou.presenter.contract;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.IndexBean;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.ui.BaseView;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:
 */

public interface AppInfoContract {

    interface View extends BaseView {
        void showResult(IndexBean indexBean);
        void onRequestPermissonSuccess();
        void onRequestPermissonError();
    }

    interface AppInfoView extends BaseView{
        void  showResult(PageBean<AppInfo> page);
        void onLoadMoreComplete();
    }

    interface  AppDetailView extends BaseView{
        void showAppDetail(AppInfo appInfo);
    }

}
