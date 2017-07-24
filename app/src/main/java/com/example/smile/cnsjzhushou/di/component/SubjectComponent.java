package com.example.smile.cnsjzhushou.di.component;


import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.di.module.SubjectModule;
import com.example.smile.cnsjzhushou.ui.fragment.BaseSubjectFragment;

import dagger.Component;


@FragmentScope
@Component(modules = SubjectModule.class,dependencies= AppComponent.class)
public interface SubjectComponent {

    void  inject(BaseSubjectFragment fragment);
}
