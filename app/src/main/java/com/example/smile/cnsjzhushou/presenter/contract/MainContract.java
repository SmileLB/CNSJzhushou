package com.example.smile.cnsjzhushou.presenter.contract;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.requestbean.AppsUpdateBean;
import com.example.smile.cnsjzhushou.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;


public class MainContract {

    public interface MainView extends BaseView {
        void requestPermissonSuccess();
        void requestPermissonFail();
        void changeAppNeedUpdateCount(int count);
    }

    public interface IMainModel {
        Observable<BaseBean<List<AppInfo>>> getUpdateApps(AppsUpdateBean param);
    }
}
