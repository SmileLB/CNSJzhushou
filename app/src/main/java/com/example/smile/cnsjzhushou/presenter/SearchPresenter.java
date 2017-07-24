package com.example.smile.cnsjzhushou.presenter;


import com.example.smile.cnsjzhushou.bean.SearchResult;
import com.example.smile.cnsjzhushou.common.rx.RxHttpReponseCompat;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ProgressSubcriber;
import com.example.smile.cnsjzhushou.presenter.contract.SearchContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class SearchPresenter extends BasePresenter<SearchContract.ISearchModel, SearchContract.SearchView> {

    @Inject
    public SearchPresenter(SearchContract.ISearchModel iSearchModel, SearchContract.SearchView searchtView) {
        super(iSearchModel, searchtView);
    }

    public void getSuggestions(String keyword) {

        mModel.getSuggestion(keyword)
                .compose(RxHttpReponseCompat.<List<String>>compatResult())
                .subscribe(new ProgressSubcriber<List<String>>(mContext, mView) {
                    @Override
                    public void onNext(List<String> suggestions) {

                        mView.showSuggestions(suggestions);
                    }
                });

    }


    public void search(String keyword) {

        saveSearchHistory(keyword);

        mModel.search(keyword)
                .compose(RxHttpReponseCompat.<SearchResult>compatResult())
                .subscribe(new ProgressSubcriber<SearchResult>(mContext, mView) {
                    @Override
                    public void onNext(SearchResult searchResult) {
                        mView.showSearchResult(searchResult);
                    }
                });

    }

    private void saveSearchHistory(String keyword) {

        // save to database
    }


    public void showHistory() {

        // get search history from  database

        List<String> list = new ArrayList<>();
        list.add("地图");
        list.add("KK");
        list.add("爱奇艺");
        list.add("播放器");
        list.add("支付宝");
        list.add("微信");
        list.add("QQ");
        list.add("TV");
        list.add("直播");
        list.add("妹子");
        list.add("美女");

        mView.showSearchHistory(list);


    }

}
