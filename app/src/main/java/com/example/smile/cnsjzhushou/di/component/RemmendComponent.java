package com.example.smile.cnsjzhushou.di.component;

import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.di.module.RemmendModule;
import com.example.smile.cnsjzhushou.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:定义Component,作为Container和Module的连接器
 */
@FragmentScope
@Component(modules =RemmendModule.class,dependencies = AppComponent.class)
public interface RemmendComponent {

    void inject(RecommendFragment fragment);

}
