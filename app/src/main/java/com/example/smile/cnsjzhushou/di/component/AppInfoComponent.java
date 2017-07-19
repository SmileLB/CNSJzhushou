package com.example.smile.cnsjzhushou.di.component;

import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.di.module.AppInfoModule;
import com.example.smile.cnsjzhushou.ui.fragment.CategoryAppFragment;
import com.example.smile.cnsjzhushou.ui.fragment.GamesFragment;
import com.example.smile.cnsjzhushou.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by LiBing
 * on 2017/7/12 0012
 * describe:
 */
@FragmentScope
@Component(modules = AppInfoModule.class,dependencies = AppComponent.class)
public interface AppInfoComponent {

    void injectTopListFragment(TopListFragment fragment);

    void injectGamesFragment(GamesFragment fragment);

    void injectCategoryAppFragment(CategoryAppFragment fragment);

}
