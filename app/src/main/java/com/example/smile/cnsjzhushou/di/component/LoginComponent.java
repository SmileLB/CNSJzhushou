package com.example.smile.cnsjzhushou.di.component;

import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.di.module.LoginModule;
import com.example.smile.cnsjzhushou.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by LiBing
 * on 2017/7/14 0014
 * describe:
 */
@FragmentScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activty);
}
