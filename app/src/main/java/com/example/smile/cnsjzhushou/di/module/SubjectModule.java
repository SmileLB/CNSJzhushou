package com.example.smile.cnsjzhushou.di.module;


import com.example.smile.cnsjzhushou.data.SubjectModel;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.presenter.contract.SubjectContract;

import dagger.Module;
import dagger.Provides;



@Module
public class SubjectModule {

    private SubjectContract.SubjectView mView;

    public SubjectModule(SubjectContract.SubjectView view){
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public SubjectContract.ISubjectModel provideModel(ApiService apiService){
        return  new SubjectModel(apiService);
    }

    @FragmentScope
    @Provides
    public SubjectContract.SubjectView provideView(){
        return  mView;
    }
}
