package com.example.smile.cnsjzhushou.data;

import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.Category;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.presenter.contract.CategoryContract;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by LiBing
 * on 2017/7/15 0015
 * describe:
 */

public class CateogryModel implements CategoryContract.ICagegoryModel {

    private ApiService mApiService;

    public CateogryModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return mApiService.getCategories();
    }
}
