package com.example.smile.cnsjzhushou.data;


import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.bean.Subject;
import com.example.smile.cnsjzhushou.bean.SubjectDetail;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.presenter.contract.SubjectContract;

import io.reactivex.Observable;



public class SubjectModel implements SubjectContract.ISubjectModel {

    private ApiService mApiService;
    public SubjectModel(ApiService apiService){
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<PageBean<Subject>>> getSubjects(int page) {
        return mApiService.subjects(page);
    }

    @Override
    public Observable<BaseBean<SubjectDetail>> getSubjectDetail(int id) {
        return mApiService.subjectDetail(id);
    }
}
