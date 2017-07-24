package com.example.smile.cnsjzhushou.di.component;


import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.di.module.AppManagerModule;
import com.example.smile.cnsjzhushou.ui.fragment.AppManangerFragment;

import dagger.Component;


@FragmentScope
@Component(modules = AppManagerModule.class,dependencies = AppComponent.class)
public interface AppManagerComponent {

    void inject(AppManangerFragment fragment);

//    void inject(DownloadingFragment fragment);
//    void injectDownloaded(DownloadedFragment fragment);
//    void injectInstalled(InstalledAppAppFragment fragment);
}
