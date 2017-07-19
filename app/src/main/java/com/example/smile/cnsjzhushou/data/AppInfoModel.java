package com.example.smile.cnsjzhushou.data;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.IndexBean;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.data.http.ApiService;

import io.reactivex.Observable;


/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:排行界面的model
 */

public class AppInfoModel {


    private ApiService mApiService;

    public AppInfoModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){
//        HttpManager manager=new HttpManager();
//        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
//        mApiService.getApps("{'page':0}").enqueue(callback);

        return mApiService.getApps("{'page':0}");
    }

    public Observable<BaseBean<IndexBean>> index(){
        return  mApiService.index();
    }

    public  Observable<BaseBean<PageBean<AppInfo>>> topList(int page){
        return  mApiService.topList(page);
    }

    public  Observable<BaseBean<PageBean<AppInfo>>> games(int page){
        return  mApiService.games(page);
    }
    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory( int categoryid,  int page){

        return  mApiService.getFeaturedAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory( int categoryid, int page){

        return  mApiService.getTopListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory( int categoryid, int page){

        return  mApiService.getNewListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<AppInfo>> getAppDetail( int id){

        return  mApiService.getAppDetail(id);
    }

}
