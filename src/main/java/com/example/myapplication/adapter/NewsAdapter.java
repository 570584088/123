package com.example.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 郭仲凯 on 2016/11/14.
 */

public class NewsAdapter extends FragmentPagerAdapter{

    private List<String> title;
    private List<Fragment> views;

    public NewsAdapter(FragmentManager fm,List<String> title,List<Fragment> views) {
        super(fm);
        this.title = title;
        this.views = views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return title.get(position);
    }
}
