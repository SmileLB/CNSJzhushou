package com.example.smile.cnsjzhushou.di.component;

import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.di.module.CateogryModule;
import com.example.smile.cnsjzhushou.ui.fragment.CategoryFragment;

import dagger.Component;

/**
 * Created by LiBing
 * on 2017/7/14 0014
 * describe:
 */
@FragmentScope
@Component(modules = CateogryModule.class,dependencies = AppComponent.class)
public interface CateogryComponent {

    void inject(CategoryFragment fragment);
}
