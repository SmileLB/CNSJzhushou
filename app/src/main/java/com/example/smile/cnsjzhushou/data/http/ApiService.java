package com.example.smile.cnsjzhushou.data.http;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.IndexBean;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.bean.requestbean.LoginRequestBean;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by LiBing
 * on 2017/6/18 0018
 * describe:API接口
 */

public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

//    @GET("featured")
//    public Observable<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);


    @GET("featured2")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @GET("index")
    public  Observable<BaseBean<IndexBean>> index();

    @GET("toplist")
    public  Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);



    @POST("login")
    public Observable<BaseBean> login(@Body LoginRequestBean bean);
    //    public static final MediaType JSON
//            = MediaType.parse("application/json; charset=utf-8");
//    OkHttpClient client = new OkHttpClient();
//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }

    @FormUrlEncoded // FormBody
    @POST("login")
    public void login2(@Field("phone") String phone);

    //    FormBody.Builder builder = new FormBody.Builder();
//
//    builder.addEncoded("phone","");
//
//    body = builde.build();

//    Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }

}
