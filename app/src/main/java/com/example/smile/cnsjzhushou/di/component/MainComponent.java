package com.example.smile.cnsjzhushou.di.component;


import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.di.module.MainModule;
import com.example.smile.cnsjzhushou.ui.activity.MainActivity;

import dagger.Component;


@FragmentScope
@Component(modules = MainModule.class,dependencies= AppComponent.class)
public interface MainComponent {
    void  inject(MainActivity activity);
}
