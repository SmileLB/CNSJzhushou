package com.example.smile.cnsjzhushou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.smile.cnsjzhushou.AppApplication;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.presenter.BasePresenter;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LiBing
 * on 2017/6/29 0029
 * describe:
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder mBind;

    protected AppApplication mApplication;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //字体
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);

        setContentView(setLayout());

        mBind = ButterKnife.bind(this);

        this.mApplication = (AppApplication) getApplication();
        setupActivityComponent(mApplication.getAppComponent());

        init();
    }

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public void startActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != mBind.EMPTY) {
            mBind.unbind();
        }
    }
}
