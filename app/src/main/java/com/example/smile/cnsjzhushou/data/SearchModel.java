package com.example.smile.cnsjzhushou.data;


import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.SearchResult;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.presenter.contract.SearchContract;

import java.util.List;

import io.reactivex.Observable;


public class SearchModel implements SearchContract.ISearchModel {


    private ApiService mApiService;


    public SearchModel(ApiService apiService) {

        this.mApiService = apiService;
    }

    public Observable<BaseBean<List<String>>> getSuggestion(String keyword) {

        return mApiService.searchSuggest(keyword);

    }

    @Override
    public Observable<BaseBean<SearchResult>> search(String keyword) {
        return mApiService.search(keyword);
    }
}
