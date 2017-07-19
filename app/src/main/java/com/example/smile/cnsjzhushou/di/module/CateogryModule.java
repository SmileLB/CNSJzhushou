package com.example.smile.cnsjzhushou.di.module;

import com.example.smile.cnsjzhushou.data.CateogryModel;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.presenter.contract.CategoryContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiBing
 * on 2017/7/15 0015
 * describe:
 */
@Module
public class CateogryModule {

    private CategoryContract.ICagegoryView mView;

    public CateogryModule(CategoryContract.ICagegoryView view){
        this.mView = view;
    }

    @Provides
    public CategoryContract.ICagegoryView ProvidesCategoryView(){
        return mView;
    }

    @Provides
    public CategoryContract.ICagegoryModel ProvidesCategoryModel(ApiService service){
        return new CateogryModel(service);
    }



}
