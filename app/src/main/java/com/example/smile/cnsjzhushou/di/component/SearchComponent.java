package com.example.smile.cnsjzhushou.di.component;


import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.di.module.SearchModule;
import com.example.smile.cnsjzhushou.ui.activity.SearchActivity;

import dagger.Component;


@FragmentScope
@Component(modules = SearchModule.class,dependencies= AppComponent.class)
public interface SearchComponent {
    void  inject(SearchActivity fragment);
}
