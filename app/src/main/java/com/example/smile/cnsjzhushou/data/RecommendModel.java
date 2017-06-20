package com.example.smile.cnsjzhushou.data;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.http.ApiService;
import com.example.smile.cnsjzhushou.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:排行界面的model
 */

public class RecommendModel {

    public void getApps(Callback<PageBean<AppInfo>> callback){
        HttpManager manager=new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        apiService.getApps("{'page':0}").enqueue(callback);
    }

}
