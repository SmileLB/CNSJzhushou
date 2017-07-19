package com.example.smile.cnsjzhushou.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.smile.cnsjzhushou.ui.bean.FragmentInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/6/18 0018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mFragments;

    public ViewPagerAdapter(FragmentManager fm, List<FragmentInfo> mFragments) {
        super(fm);
        this.mFragments = mFragments;
        // initFragments();
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
