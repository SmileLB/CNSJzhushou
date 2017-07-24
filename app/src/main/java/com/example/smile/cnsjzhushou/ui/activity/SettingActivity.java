package com.example.smile.cnsjzhushou.ui.activity;


import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.ui.fragment.SettingFragment;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;


    @Override
    public int setLayout() {
       return R.layout.template_toolbar_framelayout;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );
        mToolBar.setTitle(R.string.sys_setting);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        getFragmentManager().beginTransaction()
                .replace(R.id.content,new SettingFragment()).commit();

    }
}
