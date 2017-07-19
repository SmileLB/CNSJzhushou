package com.example.smile.cnsjzhushou;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerAppComponent;
import com.example.smile.cnsjzhushou.di.module.AppModule;
import com.example.smile.cnsjzhushou.di.module.HttpModule;


/**
 * Created by LiBing
 * on 2017/6/28 0028
 * describe:
 */

public class AppApplication extends Application{

    private AppComponent mAppComponent;

    private View mView;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent=DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public static AppApplication get(Context context){
        return (AppApplication)context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
