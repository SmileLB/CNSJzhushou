package com.example.smile.cnsjzhushou.presenter.contract;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.presenter.BasePresenter;
import com.example.smile.cnsjzhushou.ui.BaseView;

import java.util.List;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:
 */

public interface RecommendContract {

    interface View extends BaseView {
        void showNoData();
        void showError(String message);
        void showResult(List<AppInfo> datas);
    }

    interface Presenter extends BasePresenter {
        void requestDatas();
    }

}
