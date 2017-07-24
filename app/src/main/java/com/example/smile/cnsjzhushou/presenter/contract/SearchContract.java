package com.example.smile.cnsjzhushou.presenter.contract;


import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.SearchResult;
import com.example.smile.cnsjzhushou.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;


public class SearchContract {


    public interface SearchView extends BaseView {

        void showSearchHistory(List<String> list);

        void showSuggestions(List<String> list);

        void showSearchResult(SearchResult result);

    }


    public interface ISearchModel {

        Observable<BaseBean<List<String>>> getSuggestion(String keyword);

        Observable<BaseBean<SearchResult>> search(String keyword);

    }
}
