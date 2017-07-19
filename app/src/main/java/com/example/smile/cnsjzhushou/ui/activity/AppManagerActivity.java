package com.example.smile.cnsjzhushou.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.ui.adapter.ViewPagerAdapter;
import com.example.smile.cnsjzhushou.ui.bean.FragmentInfo;
import com.example.smile.cnsjzhushou.ui.fragment.DownloadingFragment;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AppManagerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private void initToolbar() {
        mToolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolbar.setTitle(R.string.download_manager);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_app_manager;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        initToolbar();
        initTablayout();
    }

    private void initTablayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),initFragments());
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private List<FragmentInfo> initFragments(){
        List<FragmentInfo> mFragments = new ArrayList<>(4);
        mFragments.add(new FragmentInfo("下载",DownloadingFragment.class));
//        mFragments.add(new FragmentInfo ("排行", TopListFragment.class));
//        mFragments.add(new FragmentInfo ("游戏", GamesFragment.class));
//        mFragments.add(new FragmentInfo ("分类", CategoryFragment.class));
        return  mFragments;
    }
}
