package com.example.smile.cnsjzhushou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.User;
import com.example.smile.cnsjzhushou.common.Constant;
import com.example.smile.cnsjzhushou.common.font.Cniao5Font;
import com.example.smile.cnsjzhushou.common.imageloader.GlideCircleTransform;
import com.example.smile.cnsjzhushou.common.rx.RxBus;
import com.example.smile.cnsjzhushou.common.util.ACache;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerMainComponent;
import com.example.smile.cnsjzhushou.di.module.MainModule;
import com.example.smile.cnsjzhushou.presenter.MainPresenter;
import com.example.smile.cnsjzhushou.presenter.contract.MainContract;
import com.example.smile.cnsjzhushou.ui.adapter.ViewPagerAdapter;
import com.example.smile.cnsjzhushou.ui.bean.FragmentInfo;
import com.example.smile.cnsjzhushou.ui.fragment.CategoryFragment;
import com.example.smile.cnsjzhushou.ui.fragment.GamesFragment;
import com.example.smile.cnsjzhushou.ui.fragment.RecommendFragment;
import com.example.smile.cnsjzhushou.ui.fragment.TopListFragment;
import com.example.smile.cnsjzhushou.ui.widget.BadgeActionProvider;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private View headerView;

    private ImageView mUserHeadView;
    private TextView mTextUserName;
    private BadgeActionProvider badgeActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    private List<FragmentInfo> initFragments() {
        List<FragmentInfo> mFragments = new ArrayList<>(4);
        mFragments.add(new FragmentInfo("推荐", RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行", TopListFragment.class));
        mFragments.add(new FragmentInfo("游戏", GamesFragment.class));
        mFragments.add(new FragmentInfo("分类", CategoryFragment.class));
        return mFragments;
    }

    @Override
    public void init() {


        RxBus.getDefault().toObservable(User.class).subscribe(new Consumer<User>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull User user) throws Exception {
                initUserHeadView(user);
                headerView.setOnClickListener(null);
            }
        });

        mPresenter.requestPermisson();

        mPresenter.getAppUpdateInfo();

    }

    private void initToolbar() {

        mToolbar.inflateMenu(R.menu.toolbar_menu);
//        Menu menu  = mToolBar.getMenu();

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_search) {
                    startActivity(new Intent(MainActivity.this, SearchActivity.class));
                }

                return true;
            }
        });

        MenuItem downloadMenuItem = mToolbar.getMenu().findItem(R.id.action_download);
        badgeActionProvider = (BadgeActionProvider) MenuItemCompat.getActionProvider(downloadMenuItem);
        badgeActionProvider.setIcon(DrawableCompat.wrap(new IconicsDrawable(this, Cniao5Font.Icon.cniao_download).color(ContextCompat.getColor(this, R.color.white))));

        badgeActionProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAppManagerActivity(badgeActionProvider.getBadgeNum() > 0 ? 2 : 0);
            }
        });
    }

    private void toAppManagerActivity(int position) {
        Intent intent = new Intent(MainActivity.this, AppManagerActivity.class);
        intent.putExtra(Constant.POSITION, position);
        startActivity(new Intent(intent));
    }

    private void initTabLayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragments());
        mViewpager.setOffscreenPageLimit(adapter.getCount());
        mViewpager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    private void initDrawerLayout() {
        //侧滑菜单的头部
        headerView = mNavigation.getHeaderView(0);

        mUserHeadView = (ImageView) headerView.findViewById(R.id.img_avatar);
        mUserHeadView.setImageDrawable(new IconicsDrawable(this, Cniao5Font.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName = (TextView) headerView.findViewById(R.id.txt_username);

        mNavigation.getMenu().findItem(R.id.menu_app_update).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_loop));
        mNavigation.getMenu().findItem(R.id.menu_download_manager).setIcon(new IconicsDrawable(this, Cniao5Font.Icon.cniao_download));
        mNavigation.getMenu().findItem(R.id.menu_app_uninstall).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline));
        mNavigation.getMenu().findItem(R.id.menu_setting).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_gear_outline));
        mNavigation.getMenu().findItem(R.id.menu_logout).setIcon(new IconicsDrawable(this, Cniao5Font.Icon.cniao_shutdown));


        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_logout:
                        logout();
                        break;
                    case R.id.menu_download_manager:
                        toAppManagerActivity(1);
                        break;
                    case R.id.menu_app_uninstall:
                        toAppManagerActivity(3);
                        break;
                    case R.id.menu_app_update:
                        toAppManagerActivity(2);
                        break;
                    case R.id.menu_setting:
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        break;
                }
                return false;
            }
        });


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this
                , mDrawerLayout
                , mToolbar
                , R.string.open
                , R.string.close);

        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);

    }

    private void logout() {

        ACache aCache = ACache.get(this);

        aCache.put(Constant.TOKEN, "");
        aCache.put(Constant.USER, "");

        mUserHeadView.setImageDrawable(new IconicsDrawable(this, Cniao5Font.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName.setText("未登录");

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        Toast.makeText(MainActivity.this, "您已退出登录", Toast.LENGTH_LONG).show();
    }

    private void initUser() {

        Object objUser = ACache.get(this).getAsObject(Constant.USER);

        if (objUser == null) {

            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });

        } else {
            User user = (User) objUser;
            initUserHeadView(user);
        }
    }

    private void initUserHeadView(User user) {
        Glide.with(this).load(user.getLogo_url()).transform(new GlideCircleTransform(this)).into(mUserHeadView);
        mTextUserName.setText(user.getUsername());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //RxBus.get().unregister(this);
    }

    @Override
    public void requestPermissonSuccess() {
        initToolbar();
        initDrawerLayout();
        initTabLayout();
        initUser();
    }

    @Override
    public void requestPermissonFail() {
        Toast.makeText(MainActivity.this, "授权失败....", Toast.LENGTH_LONG).show();
    }

    @Override
    public void changeAppNeedUpdateCount(int count) {
        if (count > 0) {
            badgeActionProvider.setText(count + "");
        } else {
            badgeActionProvider.hideBadge();
        }
    }
}
