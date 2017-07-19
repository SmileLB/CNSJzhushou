package com.example.smile.cnsjzhushou.di.component;

import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.di.module.AppDetailModule;
import com.example.smile.cnsjzhushou.ui.fragment.AppDetailFragment;

import dagger.Component;

/**
 * Created by LiBing
 * on 2017/7/15 0015
 * describe:
 */
@FragmentScope
@Component(modules = AppDetailModule.class,dependencies = AppComponent.class)
public interface AppDetailComponent {

    void inject(AppDetailFragment fragment);

}