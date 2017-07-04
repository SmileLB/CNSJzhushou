package com.example.smile.cnsjzhushou.data.http;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.PageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LiBing
 * on 2017/6/18 0018
 * describe:API接口
 */

public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);


}
